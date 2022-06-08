package com.wangbing.springboottravel.Vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wangbing.springboottravel.domain.Route;
import com.wangbing.springboottravel.domain.User;
import lombok.Data;

import java.io.Serializable;

/**
 * 收藏实体类
 */
@Data
public class FavoriteVo implements Serializable {
    private Route route;//旅游线路对象
    private String date;//收藏时间
    private User user;//所属用户
}
