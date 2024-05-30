package org.sparta.spartaassignment2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.sparta.spartaassignment2.dto.CommentRequestDto;
import org.sparta.spartaassignment2.dto.CommentResponseDto;
import org.sparta.spartaassignment2.entity.Comment;
import org.sparta.spartaassignment2.entity.Schedule;
import org.sparta.spartaassignment2.repository.CommentRepository;
import org.sparta.spartaassignment2.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j(topic = "서비스 테스트")
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto addComment(CommentRequestDto requestDto) {
        // 예외처리 : 선택한 일정의 ID를 입력 받지 않은 경우
        if (requestDto.getScheduleId() == null) {
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

    @Transactional
    public CommentResponseDto updateComment(CommentRequestDto requestDto, Long commentId) {
        if (requestDto.getScheduleId() == null) {
            throw new NullPointerException("일정ID가 입력되지 않았습니다.");
        }
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NullPointerException("등록된 댓글이 없습니다.")
        );
        scheduleRepository.findById(requestDto.getScheduleId()).orElseThrow(
                () -> new NullPointerException("등록된 일정이 없습니다.")
        );
        if (!comment.getManager().equals(requestDto.getManager())) {
            throw new IllegalArgumentException("올바른 사용자가 아닙니다.");
        }

        comment.update(requestDto);
        return new CommentResponseDto(comment);
    }

    public List<CommentResponseDto> getCommentAll() {
        return commentRepository.findAll().stream().map(CommentResponseDto::new).toList();
    }

    public String deleteComment(Long commentId, String manager) {
        if (commentId == null) {
            throw new NullPointerException("선택된 댓글이 없습니다.");
        }
        if (manager == null) {
            throw new NullPointerException("사용자가 인식되지 않았습니다.");
        }
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 댓글입니다.")
        );
        scheduleRepository.findById(comment.getSchedule().getId()).orElseThrow(
                () -> new NullPointerException("존재하지 않는 일정입니다.")
        );
        if (!comment.getManager().equals(manager)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "작성자만 삭제/수정할 수 있습니다.");
        }
        commentRepository.deleteById(commentId);
        return "댓글 삭제 완료!";
    }
}