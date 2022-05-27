package com.wangbing.springboottravel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangbing.springboottravel.dao.userDao;
import com.wangbing.springboottravel.domain.User;
import com.wangbing.springboottravel.service.UserService;
import com.wangbing.springboottravel.util.MailUtils;
import com.wangbing.springboottravel.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<userDao, User> implements UserService {

    @Autowired
    public userDao userDao;

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        //根据用户名查询用户对象
        String name = user.getName();
        User u = baseMapper.findByUsername(name);
        if (u != null) {
            //用户名存在
            return false;
        }
        //保存用户信息
        //设置激活码
        user.setCode(UuidUtil.getUuid());
        //设置激活状态
        user.setStatus("N");
        baseMapper.save(user);
        String content = "<a href='http://localhost:8808/travel/user/active?code=" + user.getCode() + "'>点击激活【黑马旅游网】</a>";
        MailUtils.sendMail(user.getEmail(), content, "激活邮件");
        return true;
    }

    /**
     * 激活用户
     */
    @Override
    public boolean active(String code) {
        //1.根据激活码查询用户对象
        User user = baseMapper.findByCode(code);
        if (user != null) {
            //2.调用dao的修改激活状态的方法
            baseMapper.updateStatus(user);
            return true;
        } else {
            return false;
        }

    }

    /**
     * 根据用户名和密码查询的方法
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("password", user.getPassword());
        return userDao.selectOne(queryWrapper);
    }
}
