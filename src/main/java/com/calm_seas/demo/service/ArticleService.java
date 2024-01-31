package com.calm_seas.demo.service;

import com.calm_seas.demo.pojo.Article;
import com.calm_seas.demo.pojo.PageBean;
import org.springframework.stereotype.Service;


public interface ArticleService {
    void add(Article article);

    void update(Article article);

    Article checkout(Integer id);

    void delete(Integer id);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryid, String state);
}
