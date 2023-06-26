package cn.itcast.travel.service.inter;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.pageBean;

public interface RouteServiceinterface {
    pageBean findPage(int currentPage,int pageSize,int cid,String rname);

    Route findOne(int rid);
}
