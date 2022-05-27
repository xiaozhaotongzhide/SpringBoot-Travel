package com.wangbing.springboottravel;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wangbing.springboottravel.dao.userDao;
import com.wangbing.springboottravel.domain.User;
import com.wangbing.springboottravel.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SpringBootTravelApplicationTests {
    @Autowired
    private UserService userService;

    @Autowired
    private userDao userDao;

    @Test
    void contextLoads() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", "bingbing6");
        queryWrapper.eq("password", "123456");
        User one = userDao.selectOne(queryWrapper);
        System.out.println(one);
    }

}
