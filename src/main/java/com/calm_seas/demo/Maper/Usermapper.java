package com.calm_seas.demo.Maper;

import com.calm_seas.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface Usermapper {
    //根据名查询
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    @Select("insert into user(username,password,create_time,updata_time)"+
    " values(#{username},#{password},now(),now())")
    void add(String username, String password);
}
