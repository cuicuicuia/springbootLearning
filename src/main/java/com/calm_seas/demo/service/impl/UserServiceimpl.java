package com.calm_seas.demo.service.impl;

import com.calm_seas.demo.Maper.Usermapper;
import com.calm_seas.demo.pojo.User;
import com.calm_seas.demo.service.UserService;
import com.calm_seas.demo.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
