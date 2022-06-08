package com.wangbing.springboottravel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangbing.springboottravel.domain.RouteImg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RouteImgDao extends BaseMapper<RouteImg> {
}
