package org.sparta.spartaassignment2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.sparta.spartaassignment2.entity.Schedule;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@Schema(name = "Schedule Response DTO")
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String manager;
//    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.manager = schedule.getManager();
//        this.password = schedule.getPassword();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}