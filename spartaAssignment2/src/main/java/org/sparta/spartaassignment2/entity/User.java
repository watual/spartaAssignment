package org.sparta.spartaassignment2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sparta.spartaassignment2.dto.UserRequestDto;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Timestamp;

@Entity
@Table
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends TimestampedOne{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nickname;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private String auth;

    public User(UserRequestDto requestDto){
        this.nickname = requestDto.getNickname();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.auth = requestDto.getAuth();
    }

    public User(UserRequestDto requestDto, String password) {
        this.nickname = requestDto.getNickname();
        this.username = requestDto.getUsername();
        this.password = password;
        this.auth = requestDto.getAuth();
    }
}