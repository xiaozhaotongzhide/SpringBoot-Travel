package com.wangbing.springboottravel.service.impl;

import com.wangbing.springboottravel.dao.FavoriteDao;
import com.wangbing.springboottravel.dao.RouteDao;
import com.wangbing.springboottravel.dao.RouteImgDao;
import com.wangbing.springboottravel.dao.SellerDao;
import com.wangbing.springboottravel.domain.PageBean;
import com.wangbing.springboottravel.domain.Route;
import com.wangbing.springboottravel.domain.RouteImg;
import com.wangbing.springboottravel.domain.Seller;
import com.wangbing.springboottravel.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteDao routeDao;

    @Autowired
    private RouteImgDao routeImgDao;

    @Autowired
    private SellerDao sellerDao;

    @Autowired
    private FavoriteDao favoriteDao;

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        //封装pageBean
        PageBean<Route> pb = new PageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示的条数
        pb.setPageSive(pageSize);

        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);
        //设置当前页面的数据集合
        int start = (currentPage - 1)*pageSize;//开始的记录数
        List<Route> list = routeDao.findByPageID(cid,start,pageSize,rname);
        pb.setList(list);
        //设置总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Route findOne(String rid) {
        //1.根据id去route表中查询route对象
        Route route = routeDao.findOne(Integer.parseInt(rid));
        //2.1根据route的id查询图片信息
        List<RouteImg> list = routeImgDao.findByRid(Integer.parseInt(rid));
        //2.2将集合设置到route对象
        route.setRouteImgList(list);
        //3.根据route的sid查询卖家的信息
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);
        //4.查询收藏次数
        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);
        return route;
    }


}
