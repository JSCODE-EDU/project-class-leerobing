package com.example.boardproject.controller;

import com.example.boardproject.domain.Board;
import com.example.boardproject.dto.BoardRequestDto;
import com.example.boardproject.dto.BoardResponseDto;
import com.example.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/write")
    public BoardResponseDto boardWrite(@RequestBody BoardRequestDto boardRequestDto) {
        Board board = boardService.write(boardRequestDto);
        return BoardResponseDto.from(board);

    }
    @GetMapping("/{id}")
    public Board boardRead(@PathVariable Long id) {
        return boardService.find(id);
    }

    @GetMapping("/find-all")
    public List<Board> boardReadAll() {
        return boardService.findAll();
    }

    @GetMapping()
    public List<Board> search(@RequestParam("keyword") String keyword) {
        return boardService.searchFind(keyword);
    }

    @PatchMapping("/{id}")
    public BoardResponseDto boardModify(@PathVariable("id") Long id, @RequestBody BoardRequestDto boardRequestDto) {
        Board board = boardService.modify(id,boardRequestDto);
        return BoardResponseDto.from(board);
    }

    @DeleteMapping("/{id}")
    public String boardDelete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return "ok";
    }


}
