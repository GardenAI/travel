package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.inter.registerDaoInterface;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class registerDaoImpl implements registerDaoInterface {
    private JdbcTemplate temp = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findUserByName(String username) {
        User user;
        try{
            String sql = "select * from tab_user where username = ?";
            user = temp.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }catch (Exception e){
            user=null;
        }
        return user;
    }

    @Override
    public boolean save(User user) {
        try{
            String sql ="insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
            temp.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getStatus(),user.getCode());
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public User findUserByCode(String code) {
        User user = null;
        try{
            String sql = "select * from tab_user where code = ?";
            user = temp.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), code);
        }catch (Exception e){
            e.printStackTrace();
            user = null;
        }
        return user;
    }

    @Override
    public boolean updateStatus(String code) {
        try{
            String sql = "update tab_user set status = 'Y' where code = ?";
            temp.update(sql, code);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        User user = null;
        try{
            String sql = "select * from tab_user where username = ? and password = ?";
            user = temp.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
