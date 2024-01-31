package com.calm_seas.demo.Maper;

import com.calm_seas.demo.pojo.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface Categorymapper {
    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time)"+
    "values (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);
    @Select("select * from category where create_user=#{id}")
    List<Category> list(Integer id);

    @Select("select * from category")
    List<Category> getlist(Integer id);
@Update("update category set category_name=#{category_name},category_alias=#{category_alias},update_time=#{update_time} where id=#{id}")
    void update(Category category);

@Delete("delete from category where id=#{id}")
    void delete(Integer id);
}
