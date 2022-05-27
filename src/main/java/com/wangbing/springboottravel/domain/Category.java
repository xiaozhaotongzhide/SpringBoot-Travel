package com.wangbing.springboottravel.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 分类实体类
 */
@Data
@TableName("tab_category")
public class Category implements Serializable {

    private int cid;//分类id

    private String cname;//分类名称
}
