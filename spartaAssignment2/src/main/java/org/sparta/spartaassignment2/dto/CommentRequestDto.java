package org.sparta.spartaassignment2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRequestDto {
    // 댓글 고유번호와 작성일자는 Entity 에서 자동생성
    // 댓글 내용, 사용자 아이디, 일정 아이디 는 외부에서 입력받음
    private String text;        // 댓글 내용
    private String manager;     // 사용자 아이디
    private Long scheduleId;    // 일정 아이디
}
