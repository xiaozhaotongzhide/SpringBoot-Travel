package com.wangbing.springboottravel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wangbing.springboottravel.dao.CategoryDao;
import com.wangbing.springboottravel.domain.Category;
import com.wangbing.springboottravel.service.CategoryService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@CacheConfig
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> findAll() {

        List<Category> cs = categoryDao.selectList(new QueryWrapper<>());

        return cs;
    }
}
