package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.registerDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.inter.registerServiceInterface;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class registerServiceImpl implements registerServiceInterface {
    private registerDaoImpl dao = new registerDaoImpl();
    @Override
    public Boolean register(User user) {
        if(dao.findUserByName(user.getUsername())==null){
            user.setCode(UuidUtil.getUuid());
            user.setStatus("N");
            Boolean b = dao.save(user);
            if(b){
                String content = "<a href='http://localhost/travel/userRL/activeRegister?code="+user.getCode()+"'>点击激活</a>";
                MailUtils.sendMail(user.getEmail(),content,"激活邮件");
            }
            return b;
        }else {
            return false;
        }
    }

    @Override
    public boolean active(String code) {
        User user = dao.findUserByCode(code);
        if (user != null){
            boolean b = dao.updateStatus(code);
            if (b) {
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }

    }

    @Override
    public User login(User user) {
        User u = dao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
        return u;
    }

}
