package com.example.boardproject.dto;

import com.example.boardproject.domain.Board;
import com.example.boardproject.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private Member member;

    @Builder
    private BoardResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;

            }

    public static BoardResponseDto from(Board board) {
        return new BoardResponseDto(board.getId(),board.getTitle(),board.getContent());
    }



}
