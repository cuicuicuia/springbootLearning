package com.calm_seas.demo.control;

import com.calm_seas.demo.pojo.Category;
import com.calm_seas.demo.pojo.Result;
import com.calm_seas.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/category")
public class CategoryContrller {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public Result add(@RequestBody Category category){
        categoryService.add(category);
        return Result.success();
    }
    @GetMapping()
    public Result<List<Category>> list(Integer id){
      List<Category> c=categoryService.list(id);
        return Result.success(c);
    }
    @GetMapping("/detail")
    public Result<List<Category>> getlist( Integer id){
        List<Category>  c=categoryService.getlist(id);
        return Result.success(c);
    }

    @PutMapping
    public Result update(@RequestBody Category category){
           categoryService.update(category);
           return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam Integer id)
    {
        categoryService.delete(id);
        return Result.success();
    }

}
