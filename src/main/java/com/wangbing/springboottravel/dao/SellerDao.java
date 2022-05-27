package com.wangbing.springboottravel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangbing.springboottravel.domain.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SellerDao extends BaseMapper<Seller> {
    public Seller findById(int id);
}
