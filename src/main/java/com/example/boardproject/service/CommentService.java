package com.example.boardproject.service;


import com.example.boardproject.domain.Comment;
import com.example.boardproject.dto.comment.CommentResponseDto;
import com.example.boardproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponseDto save(Comment comment) {
        commentRepository.save(comment);
        return CommentResponseDto.from(comment);
    }
}
