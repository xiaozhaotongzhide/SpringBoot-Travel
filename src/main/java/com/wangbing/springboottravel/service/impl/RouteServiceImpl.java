package com.wangbing.springboottravel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangbing.springboottravel.Vo.RouteVo;
import com.wangbing.springboottravel.dao.FavoriteDao;
import com.wangbing.springboottravel.dao.RouteDao;
import com.wangbing.springboottravel.dao.RouteImgDao;
import com.wangbing.springboottravel.dao.SellerDao;
import com.wangbing.springboottravel.domain.Favorite;
import com.wangbing.springboottravel.domain.PageBean;
import com.wangbing.springboottravel.domain.Route;
import com.wangbing.springboottravel.domain.RouteImg;
import com.wangbing.springboottravel.domain.Seller;
import com.wangbing.springboottravel.service.RouteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        //封装pageBean
        PageBean<Route> pb = new PageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示的条数
        pb.setPageSive(pageSize);

        //设置总记录数
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(cid) && cid != 0) {
            queryWrapper.eq("cid", cid);
        }
        if (!StringUtils.isEmpty(rname)) {
            queryWrapper.eq("rname", rname);
        }
        int totalCount = routeDao.selectCount(queryWrapper).intValue();
        pb.setTotalCount(totalCount);
        //设置当前页面的数据集合
        int start = (currentPage - 1) * pageSize;//开始的记录数
        queryWrapper.clear();
        if (!ObjectUtils.isEmpty(cid)) {
            queryWrapper.eq("cid", cid);
        }
        if (!ObjectUtils.isEmpty(cid)) {
            queryWrapper.like("rname", rname);
        }
        Page<Route> page = new Page<>(start, pageSize);
        Page selectPage = routeDao.selectPage(page, queryWrapper);
        List<Route> list = selectPage.getRecords();
        pb.setList(list);
        //设置总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public RouteVo findOne(String rid) {
        //1.根据id去route表中查询route对象
        QueryWrapper<Route> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rid", Integer.parseInt(rid));
        Route route = routeDao.selectOne(queryWrapper);
        RouteVo routeVo = new RouteVo();
        BeanUtils.copyProperties(route, routeVo);
        //2.1根据route的id查询图片信息
        QueryWrapper<RouteImg> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("rid", rid);
        List<RouteImg> list = routeImgDao.selectList(queryWrapper1);
        //2.2将集合设置到route对象
        routeVo.setRouteImgList(list);
        //3.根据route的sid查询卖家的信息
        QueryWrapper<Seller> sellerQueryWrapper = new QueryWrapper<>();
        sellerQueryWrapper.eq("sid", routeVo.getSid());
        Seller seller = sellerDao.selectOne(sellerQueryWrapper);
        routeVo.setSeller(seller);
        //4.查询收藏次数
        QueryWrapper<Favorite> favoriteQueryWrapper = new QueryWrapper<>();
        int count = favoriteDao.selectList(favoriteQueryWrapper).size();
        routeVo.setCount(count);
        return routeVo;
    }


}
