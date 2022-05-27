package com.wangbing.springboottravel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangbing.springboottravel.domain.Favorite;
import com.wangbing.springboottravel.domain.Route;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface FavoriteDao extends BaseMapper<Favorite> {

    public Favorite findByRidAndUid(int rid,int uid);

    int findCountByRid(int rid);

    void addFavorite(int rid, int uid);

    int findCount(int uid);

    List<Route> findByPageUid(int start, int pageSize, int uid);
}
