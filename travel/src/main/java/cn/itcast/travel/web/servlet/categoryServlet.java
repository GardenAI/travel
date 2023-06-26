package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.impl.categoryServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class categoryServlet extends BaseServlet {
    categoryServiceImpl service = new categoryServiceImpl();
    //查询category所有内容
    public void findCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = service.findCategories();
        writeValue(categories,response);
    }
}
