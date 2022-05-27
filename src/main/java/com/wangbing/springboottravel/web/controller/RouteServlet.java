package com.wangbing.springboottravel.web.controller;

import com.wangbing.springboottravel.domain.PageBean;
import com.wangbing.springboottravel.domain.Route;
import com.wangbing.springboottravel.domain.User;
import com.wangbing.springboottravel.service.FavoriteService;
import com.wangbing.springboottravel.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RouteServlet extends BaseServlet {

    @Autowired
    private RouteService routesercice;

    @Autowired
    private FavoriteService favoriteService;


    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @GetMapping("travel/route/pageQuery")
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        //接受搜索参数rname
        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");

        int cid = 0;
        //处理参数
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        int pageSize = 0;
        //每页显示条数不传默认五条
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }
        int currentPage = 0;
        //处理当前页数，不传，默认第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        //3.调用service查询PageBaen对象
        PageBean<Route> pb = routesercice.pageQuery(cid, currentPage, pageSize, rname);
        //4.写回客户端
        writeValue(pb, response);
    }

    /**
     * 根据id查询一个旅游线路的详细信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @GetMapping("route/findOne")
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收id
        String rid = request.getParameter("rid");
        //2.调用service
        Route route = routesercice.findOne(rid);
        //3.转化为json
        writeValue(route, response);
    }

    /**
     * 判断当前用户是否收藏
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @GetMapping("route/isFavorite")
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取rid
        String rid = request.getParameter("rid");
        //2.获取uid
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            uid = 0;
        } else {
            uid = user.getUid();
        }
        //调用FavoriteService是否收藏
        boolean favorite = favoriteService.isFavorite(rid, uid);
        writeValue(favorite, response);
    }

    /**
     * 添加收藏
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @GetMapping("route/addFavorite")
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取rid
        String rid = request.getParameter("rid");
        //2.获取uid
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            uid = 0;
        } else {
            uid = user.getUid();
        }
        //3.调用add添加收藏
        favoriteService.addFavorite(rid, uid);
    }

    @GetMapping("route/myFavorite")
    public void myFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取uid
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            uid = 0;
        } else {
            uid = user.getUid();
        }
        //接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = 0;
        //每页显示条数不传默认五条
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 12;
        }
        int currentPage = 0;
        //处理当前页数，不传，默认第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        //调用favoriteService
        PageBean<Route> myfavorite = favoriteService.myfavorite(uid, pageSize, currentPage);
        //4.写回客户端
        writeValue(myfavorite, response);
    }
}