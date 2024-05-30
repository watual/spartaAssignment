package org.sparta.spartaassignment2.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sparta.spartaassignment2.entity.Schedule;
import org.sparta.spartaassignment2.entity.User;
import org.sparta.spartaassignment2.jwt.JwtUtil;
import org.sparta.spartaassignment2.repository.ScheduleRepository;
import org.sparta.spartaassignment2.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Slf4j(topic = "AuthFilter")
@RequiredArgsConstructor
@Component
public class AuthFilter implements Filter {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();
        log.info(url);
        if (StringUtils.hasText(url) &&
                (url.startsWith("/api/user"))
        ) {
            // 미인증, 미인가
            log.info("인증 처리를 하지 않는 URL : " + url);
            chain.doFilter(request, response);
        } else if (StringUtils.hasText(url) &&
                (url.startsWith("/api/createToken"))
        ) {
            // 로그인 인증절차
            String username = httpServletRequest.getParameter("username");
            if(userRepository.findByUsername(username).isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다.");
            }
            chain.doFilter(request, response);
        } else {
            // 페이지 인가절차
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

                User user = userRepository.findByUsername(info.getSubject()).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다.")
                );
                request.setAttribute("username",user.getUsername());
                chain.doFilter(request, response); // 다음 Filter 로 이동
            } else {
                throw new IllegalArgumentException("Not Found Token");
            }
        }
    }
}