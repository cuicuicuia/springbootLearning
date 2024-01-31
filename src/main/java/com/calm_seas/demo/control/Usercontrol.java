package com.calm_seas.demo.control;

import com.calm_seas.demo.pojo.Result;
import com.calm_seas.demo.pojo.User;
import com.calm_seas.demo.service.UserService;
import com.calm_seas.demo.utils.JwtUtil;
import com.calm_seas.demo.utils.Md5Util;
import com.calm_seas.demo.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hamcrest.Condition;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    public Result<User> userInfo(/*@RequestHeader("Authorization")String toker*/){
//        Map<String,Object> map=JwtUtil.parseToken(toker);
//        String username=(String) map.get("username");
        Map<String,Object> map= ThreadLocalUtil.get();
        String username= (String) map.get("username");
         User user = userService.findByUserName(username);
        return Result.success(user);
    }
    @PutMapping("/update")
    public Result update(@RequestBody User user)
    {
        userService.update(user);
        return Result.success();

    }
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }
    @PatchMapping("/updatePwd")
    public Result updatepwd(@RequestBody Map<String,String> params){
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");

        User byUserName = userService.findByUserName(username);
        if(!byUserName.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码不正确");
        }
        if(!rePwd.equals(newPwd)){
            return Result.error("两次密码不正确");
        }
        userService.updatePwd(newPwd);
        return Result.success();

    }

}
