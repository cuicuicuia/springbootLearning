package com.calm_seas.demo.service;

import com.calm_seas.demo.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);



    List<Category> list(Integer id);

   List<Category>  getlist(Integer id);

    void update(Category category);

    void delete(Integer id);
}
