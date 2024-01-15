package com.calm_seas.demo.control;

import com.calm_seas.demo.pojo.Result;
import com.calm_seas.demo.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleControl {

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

}
