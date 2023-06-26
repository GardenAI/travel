package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.inter.RouteImgeInterface;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteImgImpl implements RouteImgeInterface {
    JdbcTemplate tem = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<RouteImg> findImgById(int rid) {
        List<RouteImg> list = null;
        try{
            String sql = "select * from tab_route_img where rid = ?";
            list = tem.query(sql, new BeanPropertyRowMapper<>(RouteImg.class), rid);
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
