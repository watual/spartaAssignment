package org.sparta.spartaassignment2.controller;

import lombok.RequiredArgsConstructor;
import org.sparta.spartaassignment2.dto.CommentRequestDto;
import org.sparta.spartaassignment2.dto.CommentResponseDto;
import org.sparta.spartaassignment2.service.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;


    // 일정 작성
    @PostMapping("/comment")
    public CommentResponseDto addComment(@RequestBody CommentRequestDto requestDto) {
        return commentService.addComment(requestDto);
    }
}
