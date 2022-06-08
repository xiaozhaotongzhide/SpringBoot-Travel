package com.wangbing.springboottravel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangbing.springboottravel.domain.Route;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RouteDao extends BaseMapper<Route> {
}
