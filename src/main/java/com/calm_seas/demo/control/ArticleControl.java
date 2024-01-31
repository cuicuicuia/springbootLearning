package com.calm_seas.demo.control;

import com.calm_seas.demo.pojo.Article;
import com.calm_seas.demo.pojo.PageBean;
import com.calm_seas.demo.pojo.Result;
import com.calm_seas.demo.service.ArticleService;
import com.calm_seas.demo.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@Validated
@RestController
@RequestMapping("/article")
public class ArticleControl {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public Result<String> list(/*@RequestHeader(name="Authorization") String toker, HttpServletResponse response*/){
//        try {
//            Map<String, Object> claims = JwtUtil.parseToken(toker);
//        }
//        catch (Exception e){
//            response.setStatus(401);
//            return Result.error("未登录");
//
//        }
        return Result.success("所有的文章数据");
    }
    @PostMapping
    public Result add(@RequestBody Article article){
        articleService.add(article);
        return Result.success();
    }
    @PutMapping
    public Result update(@RequestBody Article article){
        articleService.update(article);
        return Result.success();
    }
    @GetMapping("/detail")
    public Result<Article> checkout(@RequestParam Integer id){
        Article a=articleService.checkout(id);
        return Result.success(a);
    }
    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        articleService.delete(id);
        return Result.success();
    }
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            Integer categoryid,
            String state
    ){
        PageBean<Article> a= articleService.list( pageNum, pageSize, categoryid, state);
        return Result.success(a);
    }




}
