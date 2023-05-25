package com.example.boardproject.dto.comment;

import com.example.boardproject.domain.Board;
import com.example.boardproject.domain.Comment;
import com.example.boardproject.domain.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDto {
    private Long id;
    private String comment;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime modifiedDate = LocalDateTime.now();
    private Board board;
    private Member member;


    public Comment toEntity() {
        Comment comments = Comment.builder()
                .id(id)
                .comment(comment)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .board(board)
                .member(member)
                .build();

        return comments;
    }
}
