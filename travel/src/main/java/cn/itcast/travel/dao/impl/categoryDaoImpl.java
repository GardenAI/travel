package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.inter.categoryDaoInterface;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class categoryDaoImpl implements categoryDaoInterface {
    JdbcTemplate tem = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findCategories() {
        List<Category> list = null;
        try{
            String sql = "select * from tab_category";
            list = tem.query(sql, new BeanPropertyRowMapper<>(Category.class));
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
