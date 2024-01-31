package com.calm_seas.demo.service.impl;

import com.calm_seas.demo.Maper.ArticleMapper;
import com.calm_seas.demo.pojo.Article;
import com.calm_seas.demo.pojo.PageBean;
import com.calm_seas.demo.service.ArticleService;
import com.calm_seas.demo.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class Articleserviiceimpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        article.setUpdate_time(LocalDateTime.now());
        article.setCreate_time(LocalDateTime.now());
        articleMapper.add(article);

    }

    @Override
    public void update(Article article) {
        article.setUpdate_time(LocalDateTime.now());
        articleMapper.update(article);
    }

    @Override
    public Article checkout(Integer id) {
       return articleMapper.checkout(id);
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryid, String state) {
        PageBean<Article> pb=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userid = (Integer)map.get("id");
        List<Article> a= articleMapper.list(userid,categoryid, state);
        Page<Article> p=(Page<Article>) a;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
