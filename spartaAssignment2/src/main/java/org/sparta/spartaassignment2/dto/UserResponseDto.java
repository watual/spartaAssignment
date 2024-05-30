package org.sparta.spartaassignment2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.sparta.spartaassignment2.entity.TimestampedOne;
import org.sparta.spartaassignment2.entity.User;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    @Setter
    private String successText;

    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String auth;
    private Timestamp date;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.auth = user.getAuth();
        this.date = user.getDate();
    }
}
