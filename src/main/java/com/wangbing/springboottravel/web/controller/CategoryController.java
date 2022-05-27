package com.wangbing.springboottravel.web.controller;

import com.wangbing.springboottravel.domain.Category;
import com.wangbing.springboottravel.service.CategoryService;
import com.wangbing.springboottravel.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class CategoryController extends BaseServlet {

    @Autowired
    private CategoryService service;

    /**
     * 查询所有
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @GetMapping("travel/category/findAll")
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询所有
        List<Category> cs = service.findAll();
        //2.序列化json返回
       writeValue(cs,response);
    }

}
