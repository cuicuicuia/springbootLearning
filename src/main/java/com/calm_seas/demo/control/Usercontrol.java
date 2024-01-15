package com.calm_seas.demo.control;

import com.calm_seas.demo.pojo.Result;
import com.calm_seas.demo.pojo.User;
import com.calm_seas.demo.service.UserService;
import com.calm_seas.demo.utils.JwtUtil;
import com.calm_seas.demo.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.hamcrest.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class Usercontrol {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(@Pattern(regexp="\\S{5,16}$")  String username,@Pattern(regexp="\\S{5,16}$") String password)
    {
        User u=userService.findByUserName(username);
        if(u==null)
        {
            userService.register(username,password);
            return Result.success();
        }
        else
        {
            return  Result.error("用户已经被占用");
        }

    }
    @PostMapping("/login")
    public Result login(@Pattern(regexp="\\S{5,16}$") String username,@Pattern(regexp="\\S{5,16}$")  String password)
    {
        User loginuser= userService.findByUserName(username);
        if(loginuser==null)
        {
            return Result.error("用户名错误");
        }
        if(Md5Util.getMD5String(password).equals(loginuser.getPassword()))
        {
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",loginuser.getId());
            claims.put("username",loginuser.getUsername());
            String toker= JwtUtil.genToken(claims);
            return Result.success(toker);
        }
        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader("Authorization")String toker){
        Map<String,Object> map=JwtUtil.parseToken(toker);
        String username=(String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

}
