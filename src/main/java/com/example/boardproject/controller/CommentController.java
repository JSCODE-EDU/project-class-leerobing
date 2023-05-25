package com.example.boardproject.controller;


import com.example.boardproject.domain.Board;
import com.example.boardproject.domain.Comment;
import com.example.boardproject.dto.CommentDto;
import com.example.boardproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/{id}")
    public Long createComment(@RequestBody CommentDto commentDto, @PathVariable("id") Long boardId ){

        return commentService.save(CommentDto.toCommentDto(commentDto,boardId));
    }

}
