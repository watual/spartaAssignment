package org.sparta.spartaassignment2.service;

import lombok.RequiredArgsConstructor;
import org.sparta.spartaassignment2.dto.UserRequestDto;
import org.sparta.spartaassignment2.dto.UserResponseDto;
import org.sparta.spartaassignment2.entity.User;
import org.sparta.spartaassignment2.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto signup(UserRequestDto requestDto) {
        if(userRepository.findByUsername(requestDto.getUsername()).isPresent()){
            throw new IllegalArgumentException("이미 등록된 회원입니다.");
        }
        User user = new User(requestDto);
        userRepository.save(user);
        return new UserResponseDto(user);
    }
}
