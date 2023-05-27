package com.example.boardproject.dto;

import com.example.boardproject.domain.Member;
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
    private String memberEmail;


    public static CommentDto toCommentDto(CommentDto comment, Long boardId,String email) {


        CommentDto commentDTO = new CommentDto();
        commentDTO.setId(comment.getId());
        commentDTO.setCommentContents(comment.getCommentContents());
        commentDTO.setCreatedDate(comment.getCreatedDate());
        commentDTO.setBoardId(boardId);
        commentDTO.setMemberEmail(email);

        return commentDTO;
    }
}