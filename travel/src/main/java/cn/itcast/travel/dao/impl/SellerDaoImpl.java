package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.inter.SellerDaoInterface;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoImpl implements SellerDaoInterface {
    JdbcTemplate tem = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Seller findbyId(int rid) {
        Seller seller = null;
        try{
            String sql = "select * from tab_seller where sid = ? ";
            seller = tem.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class), 1);
        }catch(Exception e){
            e.printStackTrace();
        }
        return seller;
    }
}
