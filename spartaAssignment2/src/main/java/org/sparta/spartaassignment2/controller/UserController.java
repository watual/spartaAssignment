package org.sparta.spartaassignment2.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sparta.spartaassignment2.dto.UserRequestDto;
import org.sparta.spartaassignment2.dto.UserResponseDto;
import org.sparta.spartaassignment2.jwt.JwtUtil;
import org.sparta.spartaassignment2.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@Valid @RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = userService.signup(requestDto);
        responseDto.setSuccessText("회원가입 성공");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
