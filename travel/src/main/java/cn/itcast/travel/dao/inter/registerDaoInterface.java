package cn.itcast.travel.dao.inter;

import cn.itcast.travel.domain.User;

public interface registerDaoInterface {
    User findUserByName(String username);
    boolean save(User user);

    User findUserByCode(String code);

    boolean updateStatus(String code);

    User findUserByUsernameAndPassword(String username,String password);
}
