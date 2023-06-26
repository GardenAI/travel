package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.inter.favoriteDaoInterface;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class favoriteDaoImpl implements favoriteDaoInterface {
    JdbcTemplate tem = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favorite findFavorite(int rid, int uid) {
        Favorite favorite = null;
        try{
            String sql = "select * from tab_favorite where rid = ? and uid = ? ";
            favorite = tem.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        }catch(Exception e){
            e.printStackTrace();
        }
        return favorite;
    }

    @Override
    public int favoriteCount(int rid) {
        int count = 0;
        try{
            String sql = "select count(*) from tab_favorite where rid = ? ";
            count = tem.queryForObject(sql,Integer.class, rid);
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void addFavorite(int rid, int uid) {
        try{
            String sql = "insert into tab_favorite values(?,?,?)";
            tem.update(sql,rid,new Date(),uid);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
