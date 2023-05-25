package com.example.boardproject.controller;

import com.example.boardproject.domain.Comment;
import com.example.boardproject.dto.comment.CommentRequestDto;
import com.example.boardproject.dto.comment.CommentResponseDto;
import com.example.boardproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/comment")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto commentRequestDto) {
        Comment comment = commentRequestDto.toEntity();
        return commentService.save(comment);
    }
}
