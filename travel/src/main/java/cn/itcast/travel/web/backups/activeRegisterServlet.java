package cn.itcast.travel.web.backups;

import cn.itcast.travel.service.impl.registerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class activeRegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charst=utf-8");
        String code = request.getParameter("code");
        boolean flag = false;
        if(code != null){
            registerServiceImpl service = new registerServiceImpl();
            flag = service.active(code);
        }
        if(flag){
            String msg = "激活成功,请<a href='login.html'>登录</a>";
            response.getWriter().write(msg);
        }else{
            response.getWriter().write("激活失败，请联系管理员！");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
