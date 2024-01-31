package com.calm_seas.demo.service.impl;

import com.calm_seas.demo.Maper.Categorymapper;
import com.calm_seas.demo.pojo.Category;
import com.calm_seas.demo.service.CategoryService;
import com.calm_seas.demo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class Categoryserviceimpl implements CategoryService {
    @Autowired
    private Categorymapper categorymapper;
    @Override
    public void add(Category category) {
        category.setUpdate_time(LocalDateTime.now());
        category.setCreate_time(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id= (Integer) map.get("id");
        category.setCreate_user(id);
        categorymapper.add(category);

    }

    @Override
    public List<Category> list(Integer id) {
       Map<String,Object>  map = ThreadLocalUtil.get();
       Integer id1=(Integer) map.get("id");
        List<Category> c= categorymapper.list(id1);
      return c;
    }

    @Override
    public List<Category> getlist(Integer id) {
        List<Category>  c=categorymapper.getlist(id);
        return c;
    }

    @Override
    public void update(Category category) {
        category.setUpdate_time(LocalDateTime.now());
        categorymapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categorymapper.delete(id);

    }
}
