package cn.itcast.travel.service.inter;

import cn.itcast.travel.domain.User;

public interface registerServiceInterface {
    Boolean register(User user);

    boolean active(String code);

    User login(User user);
}
