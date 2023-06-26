package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.inter.RouteDaoInterface;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDaoInterface {
    JdbcTemplate tem = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findtotalCountById(int cid,String rname) {
        int  total = 0;
        try{
            String sql = "select count(*) from tab_route where 1 = 1 ";
            StringBuilder stb  = new StringBuilder();
            ArrayList<Object> arr = new ArrayList<>();
            stb.append(sql);
            if (cid >= 1){
                stb.append(" and cid = ? ");
                arr.add(cid);
            }
            if (rname != null && rname.length() != 0 && !"null".equals(rname)){
                stb.append(" and rname like ? ");
                arr.add("%"+rname+"%");
            }
            sql = stb.toString();
            total = tem.queryForObject(sql, Integer.class,arr.toArray());
        }catch(Exception e){
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String rname) {
        List<Route> list = null;
        try{
            String sql = "select * from tab_route where 1 = 1 ";
            StringBuilder stb  = new StringBuilder();
            ArrayList<Object> arr = new ArrayList<>();
            stb.append(sql);
            if (cid >= 1){
                stb.append(" and cid = ? ");
                arr.add(cid);
            }
            if (rname != null && rname.length() != 0 && !"null".equals(rname)){
                stb.append(" and rname like ? ");
                arr.add("%"+rname+"%");
            }
            stb.append(" limit ? , ? ");
            arr.add(start);
            arr.add(pageSize);
            sql = stb.toString();
            list = tem.query(sql, new BeanPropertyRowMapper<>(Route.class),arr.toArray());
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Route findOne(int rid) {
        Route route = new Route();
        try{
            String sql = "select * from tab_route where rid = ?";
            route = tem.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
        }catch(Exception e){
            e.printStackTrace();
        }
        return route;
    }
}
