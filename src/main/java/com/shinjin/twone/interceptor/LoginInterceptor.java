package com.shinjin.twone.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        if (session.getAttribute("login") == null) { // 로그인 되어 있지 않은 경우
            log.info("===== preHandle =====");
            log.info("미인증 사용자에게 로그인 요청");

            response.sendRedirect("/login"); // 로그인으로 redirect
            return false;
        }
        return true;
    }
}