package com.calm_seas.demo.Maper;

import com.calm_seas.demo.pojo.Article;
import com.calm_seas.demo.pojo.PageBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article(title, content, cover_img,state,category_id,create_time, update_time,create_user)"+
            "values (#{title},#{content},#{cover_img},#{state},#{category_id},#{create_time},#{update_time},#{category_id})")
    void add(Article article);
@Update("update article set id=#{id},title=#{title},content=#{content},cover_img=#{cover_img},state=#{state},update_time=now() where category_id=#{category_id}")
    void update(Article article);

@Select("select * from article where id=#{id}")
    Article checkout(Integer id);

@Delete("delete from article where id=#{id}")
    void delete(Integer id);

    List<Article> list(Integer pageNum, Integer categoryid, String state);
}
