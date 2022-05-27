package com.wangbing.springboottravel.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wangbing.springboottravel.domain.User;

public interface UserService extends IService<User> {
    boolean regist(User user);

    boolean active(String code);

    User login(User user);
}
