package com.wangbing.springboottravel.domain;

import lombok.Data;

import java.util.List;

/**
 *分页对象
 */
@Data
public class PageBean<T> {
    private int totalCount;//总记录数
    private int totalPage;//总页数
    private int currentPage;//当前页码
    private int pageSive;//每页显示的条数

    private List<T> list;//每页显示的数据集合

}
