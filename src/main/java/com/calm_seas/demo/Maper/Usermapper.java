package com.calm_seas.demo.Maper;

import com.calm_seas.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface Usermapper {
    //根据名查询
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    @Select("insert into user(username,password,create_time,updata_time)"+
    " values(#{username},#{password},now(),now())")
    void add(String username, String password);
    @Update("update user set nickname=#{nickname},email=#{email},updata_time=#{updateTime} where id=#{id}")
    void update(User user);

    @Update("update user set user_pic=#{avatarUrl},updata_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl ,Integer id);

    @Update("update user set password=#{md5String},updata_time=now() where id=#{id}")
    void updatePwd(String md5String,Integer id);
}
