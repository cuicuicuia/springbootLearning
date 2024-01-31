package com.calm_seas.demo.pojo;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
@Data
public class User {
   @NonNull
    private Integer id;//主键ID
    private String username;//用户名
    private String password;//密码
    //@NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;//昵称
    private String email;//邮箱

    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
