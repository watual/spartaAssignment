package org.sparta.spartaassignment2.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sparta.spartaassignment2.entity.Schedule;
import org.sparta.spartaassignment2.jwt.JwtUtil;
import org.sparta.spartaassignment2.repository.CommentRepository;
import org.sparta.spartaassignment2.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Slf4j(topic = "AuthFilter")
@RequiredArgsConstructor
@Component
public class AuthFilter implements Filter {

    private final ScheduleRepository scheduleRepository;
    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();
        if (StringUtils.hasText(url) &&
                (url.startsWith("/api/createToken"))
        ) {
            // 인증절차
            String manager = httpServletRequest.getParameter("manager");
            if(scheduleRepository.findByManager(manager).isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다.");
            }
            chain.doFilter(request, response);
        } else {
            // 인가절차
            // 토큰 확인
            String tokenValue = jwtUtil.getTokenFromRequest(httpServletRequest);

            if (StringUtils.hasText(tokenValue)) { // 토큰이 존재하면 검증 시작
                // JWT 토큰 substring
                String token = jwtUtil.substringToken(tokenValue);

                // 토큰 검증
                if (!jwtUtil.validateToken(token)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "토큰이 유효하지 않습니다.");
                }

                // 토큰에서 사용자 정보 가져오기
                Claims info = jwtUtil.getUserInfoFromToken(token);

                scheduleRepository.findByManager(info.getSubject()).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다.")
                );

                chain.doFilter(request, response); // 다음 Filter 로 이동
            } else {
                throw new IllegalArgumentException("Not Found Token");
            }
        }
    }
}