package org.sparta.spartaassignment2.service;

import lombok.RequiredArgsConstructor;
import org.sparta.spartaassignment2.dto.CommentRequestDto;
import org.sparta.spartaassignment2.dto.CommentResponseDto;
import org.sparta.spartaassignment2.dto.ScheduleRequestDto;
import org.sparta.spartaassignment2.dto.ScheduleResponseDto;
import org.sparta.spartaassignment2.entity.Comment;
import org.sparta.spartaassignment2.entity.Schedule;
import org.sparta.spartaassignment2.repository.CommentRepository;
import org.sparta.spartaassignment2.repository.ScheduleRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto addComment(CommentRequestDto requestDto) {
        // 예외처리 : 선택한 일정의 ID를 입력 받지 않은 경우
        if (requestDto.getScheduleId() == null){
            throw new IllegalArgumentException("일정이 선택되지 않았습니다.");
        }
        // 예외처리 : 댓글 내용이 비어 있는 경우
        if (requestDto.getText().isBlank()) {
            throw new IllegalArgumentException("댓글 내용이 작성되지 않았습니다.");
        }
        // 예외처리 : 일정이 DB에 저장되지 않은 경우
        Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId()).orElseThrow(
                () -> new NullPointerException("존재하지 않는 일정입니다.")
        );
        Comment comment = new Comment(requestDto, schedule);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }
}
