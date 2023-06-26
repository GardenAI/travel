package cn.itcast.travel.dao.inter;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDaoInterface {
    int findtotalCountById(int cid,String rname);
    List<Route> findByPage(int cid,int start,int pageSize,String rname);
    Route findOne(int rid);
}
