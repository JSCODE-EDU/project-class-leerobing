package com.example.boardproject.controller;

import com.example.boardproject.domain.Board;
import com.example.boardproject.dto.BoardRequestDto;
import com.example.boardproject.dto.BoardResponseDto;
import com.example.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/write")
    public BoardResponseDto boardWrite(@RequestBody BoardRequestDto boardRequestDto) {
        Board board = boardService.write(boardRequestDto);
        return new BoardResponseDto(board);

    }
    @GetMapping("/{id}")
    public Board boardRead(@PathVariable Long id) {
        return boardService.find(id);
    }

}
