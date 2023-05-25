package com.example.boardproject.dto.comment;

import com.example.boardproject.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String comment;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private String nickname;

    private Long boardId;
    private Long memberId;

    private CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
        this.nickname = comment.getMember().getNickname();
        this.boardId = comment.getBoard().getId();
        this.memberId = comment.getMember().getId();
    }

    public static CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(comment);
    }

}
