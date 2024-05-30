package org.sparta.spartaassignment2.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.sparta.spartaassignment2.jwt.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

//    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final JwtUtil jwtUtil;

    @GetMapping("/createToken")
    public String createJwt(HttpServletResponse res, @RequestParam String manager) {
        // Jwt 생성
        String token = jwtUtil.createToken(manager);
        // Jwt 쿠키 저장
        jwtUtil.addJwtToCookie(token, res);

        return "토큰 생성 완료\n" + "createJwt : " + token;
    }
}