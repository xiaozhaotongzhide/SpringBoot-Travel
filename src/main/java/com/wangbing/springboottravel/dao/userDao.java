package com.wangbing.springboottravel.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangbing.springboottravel.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface userDao extends BaseMapper<User> {
    /**
     * 根据用户名查询用户信息
     */
    public User findByUsername(String username);

    public void save(User user);

    User findByCode(String code);

    void updateStatus(User user);

    User findByUserNameAndPassword(String userName,String password);
}
