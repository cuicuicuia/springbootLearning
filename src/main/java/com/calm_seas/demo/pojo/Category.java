package com.calm_seas.demo.pojo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {
    private Integer id;//主键ID
    @NotEmpty
    private String category_name;//分类名称
    @NotEmpty
    private String category_alias;//分类别名
    private Integer create_user;//创建人ID
    private LocalDateTime create_time;//创建时间
    private LocalDateTime update_time;//更新时间

}
