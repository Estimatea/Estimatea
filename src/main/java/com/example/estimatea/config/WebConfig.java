package com.example.estimatea.config;

import com.example.estimatea.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns( // Tell Spring which URLs to watch
                        "/project/all")

                .excludePathPatterns( // And these to be excluded
                        "/employee/login-page", // Need to see this to log in!
                        "/**/css/**",           // Static files must be public
                        "/**/images/**"
                );
    }
}
