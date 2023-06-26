package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.domain.pageBean;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import cn.itcast.travel.service.impl.favoriteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/route/*")
public class routeServlet extends BaseServlet {
    RouteServiceImpl service = new RouteServiceImpl();
    favoriteServiceImpl fservice = new favoriteServiceImpl();
    //获取分页
    public void getPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage_str = request.getParameter("currentPage");
        String pageSize_str = request.getParameter("pageSize");
        String cid_str = request.getParameter("cid");
        String rname = request.getParameter("rname");
        //重新编码
        //rname = new String(rname.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        int currentPage = 1;
        int pageSize = 5;
        int cid = 5;
        if (currentPage_str != null && currentPage_str.length() != 0 && !"null".equals(currentPage_str)){
            currentPage = Integer.parseInt(currentPage_str);
        }
        if (pageSize_str != null && pageSize_str.length() != 0 && !"null".equals(pageSize_str)){
            pageSize = Integer.parseInt(pageSize_str);
        }
        if (cid_str != null && cid_str.length() != 0 && !"null".equals(cid_str)){
            cid = Integer.parseInt(cid_str);
        }
        pageBean page = service.findPage(currentPage, pageSize,cid,rname);
        writeValue(page,response);
    }
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid_str = request.getParameter("rid");
        int rid = 1;
        if (rid_str != null && !"null".equals(rid_str) && !"".equals(rid_str)){
            rid = Integer.parseInt(rid_str);
        }
        RouteServiceImpl service = new RouteServiceImpl();
        Route route= service.findOne(rid);
        writeValue(route,response);
    }
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid_str = request.getParameter("rid");
        int rid = 1;
        if (rid_str != null && !"null".equals(rid_str) && !"".equals(rid_str)){
            rid = Integer.parseInt(rid_str);
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("u");
        int uid = 0;
        if (user != null){
            uid = user.getUid();
        }
        boolean flag = fservice.isFavorite(rid, uid);
        writeValue(flag,response);
    }
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid_str = request.getParameter("rid");
        int rid = 1;
        if (rid_str != null && !"null".equals(rid_str) && !"".equals(rid_str)){
            rid = Integer.parseInt(rid_str);
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("u");
        int uid = 0;
        if (user != null){
            uid = user.getUid();
        }else {
            return;
        }
        fservice.addFavorite(rid,uid);
    }
}
