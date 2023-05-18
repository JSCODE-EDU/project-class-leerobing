package com.example.boardproject.controller;

import com.example.boardproject.common.Exception;
import com.example.boardproject.domain.Board;
import com.example.boardproject.dto.BoardResponseDto;
import com.example.boardproject.dto.ModifyRequestDto;
import com.example.boardproject.dto.SaveRequestDto;
import com.example.boardproject.exception.InvalidateBoardException;
import com.example.boardproject.response.CommonResponse;
import com.example.boardproject.response.ListResponse;
import com.example.boardproject.response.ResponseService;
import com.example.boardproject.response.SingleResponse;
import com.example.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ResponseService responseService;

    @PostMapping("/write")
    public SingleResponse<Board> boardWrite(@RequestBody @Valid SaveRequestDto saveRequestDto) {
        Board board = boardService.write(saveRequestDto);
        return responseService.getSingleResponse(board);

    }
    @GetMapping("/{id}")
    public SingleResponse<Board> boardRead(@PathVariable Long id) throws InvalidateBoardException {
        return responseService.getSingleResponse(boardService.find(id));
    }

    @GetMapping("/find-all")
    public ListResponse<Board> boardReadAll() {
        return responseService.getListResponse(boardService.findAll());
    }

    @GetMapping()
    public ListResponse<Board> search(@RequestParam("keyword") String keyword) {
        return responseService.getListResponse(boardService.searchFind(keyword));
    }

    @PatchMapping("/{id}")
    public SingleResponse boardModify(@PathVariable("id") Long id,
                                        @RequestBody @Valid ModifyRequestDto modifyRequestDto) throws InvalidateBoardException {
        Board board = boardService.modify(id,modifyRequestDto);
        return responseService.getSingleResponse(board);
    }

    @DeleteMapping("/{id}")
    public String boardDelete(@PathVariable("id") Long id) throws InvalidateBoardException {
        boardService.delete(id);
        return "ok";
    }

}
