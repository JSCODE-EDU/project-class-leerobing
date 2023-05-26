package com.example.boardproject.domain;


import com.example.boardproject.dto.CommentDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String commentContents;

    @Column
    private String nickname;

    /* Board:Comment = 1:N */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    public static Comment toSaveEntity(CommentDto commentDto, Board board, Member member) {
        Comment comment = new Comment();
        comment.setCommentContents(commentDto.getCommentContents());
        comment.setBoard(board);
        comment.setNickname(member.getNickname());
        comment.setMember(member);
        return comment;
    }
}