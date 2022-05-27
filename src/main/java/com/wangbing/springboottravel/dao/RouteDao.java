package com.wangbing.springboottravel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangbing.springboottravel.domain.Route;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RouteDao extends BaseMapper<Route> {
    /**
     * 根据cid查询总记录数
     */
    public int findTotalCount(int cid ,String rname);
    /**
     * 根据cid，start，pageSize查询当前页的数据集合
     */
    public List<Route> findByPageID(int cid ,int start,int pageSize,String rname);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    public Route findOne(int rid);
}
