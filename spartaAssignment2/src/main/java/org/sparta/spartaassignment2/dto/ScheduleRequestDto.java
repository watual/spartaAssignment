package org.sparta.spartaassignment2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ScheduleRequestDto {
//    @Max(value = 2000)
    @NotBlank(message = "제목을 반드시 입력해주세요.")
    private String title;
    private String contents;
    @Email
    private String manager;
    @NotBlank(message = "비밀번호를 반드시 입력해주세요.")
    private String password;
}