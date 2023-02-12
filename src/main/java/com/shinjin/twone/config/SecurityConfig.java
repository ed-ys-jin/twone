package com.shinjin.twone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  // 비밀번호 인코더 Bean 정의
  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  //보안 필터 Chain Bean 정의
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors().disable()			// CORS(Cross-Origin Resource Sharing) 비활성화
            .csrf().disable()		// CSRF(Cross-Site Request Forgery) 보호 비활성화
            .formLogin().disable()	// 기본 로그인 페이지 비활성화
            .headers().frameOptions().disable();  // X-Frame-Options 헤더 비활성화

    return http.build();
  }
}
