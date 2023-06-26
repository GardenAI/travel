package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.dao.impl.favoriteDaoImpl;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.domain.pageBean;
import cn.itcast.travel.service.inter.RouteServiceinterface;

import java.util.List;

public class RouteServiceImpl implements RouteServiceinterface {
    RouteDaoImpl dao = new RouteDaoImpl();
    RouteImgImpl imgdao = new RouteImgImpl();
    SellerDaoImpl sellerDao = new SellerDaoImpl();
    favoriteDaoImpl fdao = new favoriteDaoImpl();
    @Override
    public pageBean findPage(int currentPage, int pageSize, int cid,String rname) {
        pageBean<Route> pageBean = new pageBean<>();
        pageBean.setCurrentpage(currentPage);
        pageBean.setPageSize(pageSize);
        int totalCount = dao.findtotalCountById(cid,rname);
        pageBean.setTotalCount(totalCount);
        int start = (currentPage-1)*pageSize;
        List<Route> list = dao.findByPage(cid, start, pageSize,rname);
        pageBean.setList(list);
        int totalpage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize)+1;
        pageBean.setTotalPage(totalpage);
        return pageBean;
    }

    @Override
    public Route findOne(int rid) {
        Route route = dao.findOne(rid);
        List<RouteImg> imgList = imgdao.findImgById(rid);
        route.setRouteImgList(imgList);
        Seller seller = sellerDao.findbyId(rid);
        route.setSeller(seller);
        int count = fdao.favoriteCount(rid);
        route.setCount(count);
        return route;
    }
}
