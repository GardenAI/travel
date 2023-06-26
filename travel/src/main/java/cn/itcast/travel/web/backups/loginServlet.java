package cn.itcast.travel.web.backups;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.registerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        registerServiceImpl service = new registerServiceImpl();
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
        response.setContentType("application/json;charst=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),info);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
