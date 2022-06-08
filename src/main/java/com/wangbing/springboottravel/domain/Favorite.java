package com.wangbing.springboottravel.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 收藏实体类
 */
@Data
@TableName("tab_favorite")
public class Favorite implements Serializable {
    private String rid;//旅游线路对象
    private Data date;//收藏时间
    private String uid;//所属用户
}
