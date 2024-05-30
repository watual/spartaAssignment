package org.sparta.spartaassignment2.service;

import lombok.RequiredArgsConstructor;
import org.sparta.spartaassignment2.dto.UserRequestDto;
import org.sparta.spartaassignment2.dto.UserResponseDto;
import org.sparta.spartaassignment2.entity.User;
import org.sparta.spartaassignment2.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserResponseDto signup(UserRequestDto requestDto) {
        String password = passwordEncoder.encode(requestDto.getPassword());
        if(userRepository.findByUsername(requestDto.getUsername()).isPresent()){
            throw new IllegalArgumentException("이미 등록된 회원입니다.");
        }
        User user = new User(requestDto, password);
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    public void login(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new NullPointerException("등록되지 않은 아이디 입니다.")
        );
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

    }
}
