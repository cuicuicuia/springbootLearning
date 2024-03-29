package com.calm_seas.demo.interceptors;

import com.calm_seas.demo.utils.JwtUtil;
import com.calm_seas.demo.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
@Component
public class Logininterceptors implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object headler)
    {
        String toker=request.getHeader("Authorization");
        try {


            Map<String,Object> claims= JwtUtil.parseToken(toker);
            ThreadLocalUtil.set(claims);
           // String username= (String) claims.get("username");



            return true;
        } catch (Exception e) {
            //throw new RuntimeException(e);
            response.setStatus(401);
            return false;
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}
