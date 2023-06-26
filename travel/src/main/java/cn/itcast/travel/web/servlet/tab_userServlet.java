package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.registerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Random;

@WebServlet("/userRL/*")
public class tab_userServlet extends BaseServlet {
    registerServiceImpl service = new registerServiceImpl();
    //账号激活
    public void activeRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charst=utf-8");
        String code = request.getParameter("code");
        boolean flag = false;
        if(code != null){
            flag = service.active(code);
        }
        if(flag){
            String msg = "激活成功,请<a href='login.html'>登录</a>";
            response.getWriter().write(msg);
        }else{
            response.getWriter().write("激活失败，请联系管理员！");
        }
    }
    //用户退出
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
    //获取用户
    public void findUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("u");
        writeValue(user,response);
    }
    //用户登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        HttpSession session = request.getSession();
        User u = service.login(user);
        ResultInfo info = new ResultInfo();
        if (u != null){
            if ("Y".equals(u.getStatus())){
                session.setAttribute("u",u);
                info.setFlag(true);
            }else {
                info.setFlag(false);
                info.setErrorMsg("该用户未激活！");
            }
        }else {
            //用户名或密码错误
            info.setFlag(false);
            info.setErrorMsg("用户或密码错误！");
        }
        writeValue(info,response);
    }
    //用户注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        ResultInfo info = new ResultInfo();
        if(!check.equalsIgnoreCase(checkcode_server) || check.equals("")){
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
        }else {
            try {
                //将map中的数据导入user对象
                BeanUtils.populate(user,map);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            Boolean flag = service.register(user);
            if (flag){
                //注册成功
                info.setFlag(true);
            }else {
                //注册失败
                info.setFlag(false);
                info.setErrorMsg("注册失败");
            }
        }
        writeValue(info,response);
    }
}
