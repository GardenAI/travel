package cn.itcast.travel.dao.inter;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgeInterface {
    List<RouteImg> findImgById(int rid);
}
