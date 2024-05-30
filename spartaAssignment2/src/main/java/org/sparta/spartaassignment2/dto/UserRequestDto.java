package org.sparta.spartaassignment2.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDto {
    private String nickname;
    @Pattern(regexp="^[0-9a-z]{4,10}$")
    private String username;
    @Pattern(regexp="^[0-9a-zA-Z]{8,15}$")
    private String password;
    private String auth;
}
