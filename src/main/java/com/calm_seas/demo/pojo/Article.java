package com.calm_seas.demo.pojo;


import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Article {
    private Integer id;//主键ID
    private String title;//文章标题
    private String content;//文章内容
    private String cover_img;//封面图像
    private String state;//发布状态 已发布|草稿
    private Integer category_id;//文章分类id
    private Integer create_user;//创建人ID
    private LocalDateTime create_time;//创建时间
    private LocalDateTime update_time;//更新时间
}
