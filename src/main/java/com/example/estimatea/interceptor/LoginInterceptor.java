package com.example.estimatea.interceptor;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws IOException {

        HttpSession currentSession = request.getSession();

        if (currentSession == null || currentSession.getAttribute("currentEmployee") == null) {
            response.sendRedirect("/employee/login");
            return false;
        }
        return true;
    }
}
