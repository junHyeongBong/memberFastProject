package org.test.memberlogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) //CSRF 비활성화 (API 사용을위해)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/auth/signup").permitAll() //회원가입은 인증없이 허가
                    .anyRequest().authenticated()
            )
            .formLogin(AbstractHttpConfigurer::disable)    //기본 로그인 페이지 비활성화
            .httpBasic(AbstractHttpConfigurer::disable); //기본 http 인증 비활성화
        return http.build();
    }
}
