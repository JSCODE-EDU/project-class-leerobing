package com.example.boardproject.dto;

import com.example.boardproject.domain.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;

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
