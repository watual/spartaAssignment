//package org.sparta.spartaassignment2.config;
//
//import io.jsonwebtoken.Jwt;
//import lombok.RequiredArgsConstructor;
//import org.sparta.spartaassignment2.filter.AuthFilter;
//import org.sparta.spartaassignment2.jwt.JwtUtil;
//import org.sparta.spartaassignment2.repository.ScheduleRepository;
//import org.sparta.spartaassignment2.repository.UserRepository;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Configuration
//@RequiredArgsConstructor
//public class WebSecurityConfig {
//
//    private final JwtUtil jwtUtil;
//    private final ScheduleRepository scheduleRepository;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception {
//
//        // CSRF 설정
//        http.csrf((csrf) -> csrf.disable());
//
//        // 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
//        http.sessionManagement((sessionManagement) ->
//                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        );
//
//        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new AuthFilter(scheduleRepository, jwtUtil));
//        registrationBean.addUrlPatterns("/api/*"); // 필터를 적용할 URL 패턴 설정
//
//        return http.build();
//    }
//}
