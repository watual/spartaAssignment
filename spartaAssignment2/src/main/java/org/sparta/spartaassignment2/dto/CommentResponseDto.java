package org.sparta.spartaassignment2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sparta.spartaassignment2.entity.Comment;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private Long commentId;
    private String text;
    private String manager;
    private Long scheduleId;
    private LocalDateTime date;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getId();
        this.text = comment.getText();
        this.manager = comment.getManager();
        this.scheduleId = comment.getSchedule().getId();
        this.date = comment.getDate();
    }
}
