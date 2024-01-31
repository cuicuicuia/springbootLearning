package com.calm_seas.demo.service.impl;

import com.calm_seas.demo.Maper.Usermapper;
import com.calm_seas.demo.pojo.User;
import com.calm_seas.demo.service.UserService;
import com.calm_seas.demo.utils.Md5Util;
import com.calm_seas.demo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private Usermapper usermapper;
    @Override
    public User findByUserName(String username) {
       User u= usermapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        //加密
       String md5= Md5Util.getMD5String(password);
       usermapper.add(username,md5);

    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        usermapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id =(Integer) map.get("id");
        usermapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id =(Integer) map.get("id");
        usermapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }
}
