package com.calm_seas.demo.service;

import com.calm_seas.demo.pojo.User;

public interface UserService {
    //根据用户名
    User findByUserName(String username);

    void register(String username, String password);
    public void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
