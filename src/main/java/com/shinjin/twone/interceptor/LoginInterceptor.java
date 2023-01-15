package com.shinjin.twone.interceptor;

import com.shinjin.twone.dto.MemDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();

        if(session.getAttribute("login") == null){
            request.setAttribute("path", "/login");
            RequestDispatcher view = request.getRequestDispatcher("redirect:/login");
            view.forward(request, response);
            return false;
        } else {
            return true;
        }
    }
}
