package org.sparta.spartaassignment2.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sparta.spartaassignment2.dto.CommentRequestDto;
import org.sparta.spartaassignment2.dto.CommentResponseDto;
import org.sparta.spartaassignment2.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment")
    public List<CommentResponseDto> getCommentAll(){
        return commentService.getCommentAll();
    }

    // 일정 작성
    @PostMapping("/comment")
    public CommentResponseDto addComment(@RequestBody CommentRequestDto requestDto) {
        return commentService.addComment(requestDto);
    }

    @PostMapping("/comment/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(requestDto, commentId);
    }

    @DeleteMapping("/comment")
    public ResponseEntity<String> deleteComment(@RequestParam Long commentId, @RequestParam String manager) {
        return new ResponseEntity<>(commentService.deleteComment(commentId, manager), HttpStatus.OK);
    }
}