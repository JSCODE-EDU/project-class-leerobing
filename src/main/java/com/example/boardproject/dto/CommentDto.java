package com.example.boardproject.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDto {
    private Long id;
    private String commentContents;
    private Long boardId;
    private LocalDateTime createdDate;

    public static CommentDto toCommentDto(CommentDto comment, Long boardId) {
        CommentDto commentDTO = new CommentDto();
        commentDTO.setId(comment.getId());
        commentDTO.setCommentContents(comment.getCommentContents());
        commentDTO.setCreatedDate(comment.getCreatedDate());
        commentDTO.setBoardId(boardId);
        return commentDTO;
    }
}