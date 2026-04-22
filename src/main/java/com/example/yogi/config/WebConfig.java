package com.example.yogi.config;

import com.example.yogi.config.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/member/**")   // 적용 경로
                .excludePathPatterns(
                        "/login",
                        "/member/signup",
                        "/member/find-id",
                        "/member/find-password",
                        "/css/**", "/js/**"
                );
    }
}