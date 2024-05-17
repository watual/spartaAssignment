package org.sparta.spartaassignment2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SchedulePasswordRequestDto {
    @NotBlank(message = "아이디를 반드시 입력해주세요.")
    private Long id;
    @NotBlank(message = "비밀번호를 반드시 입력해주세요.")
    private String password;
}