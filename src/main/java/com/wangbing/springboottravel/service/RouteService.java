package com.wangbing.springboottravel.service;

import com.wangbing.springboottravel.domain.PageBean;
import com.wangbing.springboottravel.domain.Route;

public interface RouteService {
    /**
     * 根据类别进行分页查询
     * @param cid
     * @param currentPageBean
     * @param pageSize
     * @return
     */
    public PageBean<Route> pageQuery(int cid,int currentPageBean,int pageSize,String rname);

    Route findOne(String rid);


}
