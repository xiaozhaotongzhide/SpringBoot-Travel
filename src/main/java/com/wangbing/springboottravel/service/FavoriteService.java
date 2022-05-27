package com.wangbing.springboottravel.service;

import com.wangbing.springboottravel.domain.PageBean;
import com.wangbing.springboottravel.domain.Route;

public interface FavoriteService {

    public boolean isFavorite(String rid,int uid);

    public void addFavorite(String rid, int uid);

    PageBean<Route> myfavorite(int uid, int pageSize, int currentPage);
}
