package com.example.boardproject.dto;

import com.example.boardproject.domain.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;

    private BoardResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static BoardResponseDto from(Board board) {
        return new BoardResponseDto(board.getId(),board.getTitle(),board.getContent());
    }

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
