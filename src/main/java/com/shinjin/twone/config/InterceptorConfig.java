package com.shinjin.twone.config;

import com.shinjin.twone.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /* 로그인 인터셉터 */
        registry.addInterceptor(new LoginInterceptor()) // 인터셉터 등록
                .addPathPatterns("/**") // 인터셉터를 적용할 url 패턴
                .excludePathPatterns("/", "/login", "/signup", "/resources/**"); // 인터셉터에서 제외할 패턴 지정
    }

}
