package com.calm_seas.demo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Test
  public void contextLoads() {
      Map<String,Object> claims=new HashMap<>();
      claims.put("id",1);
      claims.put("username","张三");
      String toker=  JWT.create().withClaim("user",claims)//增加载荷
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))//增加有效期
                .sign(Algorithm.HMAC256("calmseas"));
        System.out.println(toker);
    }
    @Test
    public void teast(){
        String toker="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MDU2ODg0NjR9" +
                ".qynE5SBuP3TWBnHHfFI3MRxBPXzyeIAiEoqELbHFIao";
        JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256("calmseas")).build();
        DecodedJWT   decodedJWT=jwtVerifier.verify(toker);
        Map<String, Claim> claims=decodedJWT.getClaims();
        System.out.println(claims.get("user"));
    }

}
