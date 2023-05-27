package com.example.boardproject.controller;

import com.example.boardproject.domain.Board;
import com.example.boardproject.dto.ModifyRequestDto;
import com.example.boardproject.dto.SaveRequestDto;
import com.example.boardproject.exception.InvalidateBoardException;
import com.example.boardproject.response.ListResponse;
import com.example.boardproject.response.ResponseService;
import com.example.boardproject.response.SingleResponse;
import com.example.boardproject.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Api(tags = "게시판 컨트롤러")
public class BoardController {

    private final BoardService boardService;
    private final ResponseService responseService;

    @ApiOperation(value = "게시글 작성 기능", notes = "제목, 내용을 입력해 게시글을 작성한다.")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시글 작성 성공."),
            @ApiResponse(code = 400, message = "제목 또는 내용이 유효성 검사에서 실패할 때.", response = String.class)
    })
    @PostMapping("/write")
    public SingleResponse<Board>boardWrite(@RequestBody @Valid SaveRequestDto saveRequestDto) {
        Board board = boardService.write(saveRequestDto);
        return responseService.getSingleResponse(board);

    }
    @ApiOperation(value = "게시글 조회 기능", notes = "특정 게시글만 조회하는 api")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "존재하지 않는 게시글입니다."),
            @ApiResponse(code = 500, message = "서버 내부 오류입니다 관리자에게 문의 부탁드립니다.")

    })
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{id}")
    public SingleResponse<Board> boardRead(@PathVariable Long id) throws InvalidateBoardException {
        return responseService.getSingleResponse(boardService.find(id));
    }

    @ApiOperation(value = "게시글 전체 조회 기능", notes = "전체 게시글을 조회하는 api")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "존재하지 않는 게시글입니다."),
            @ApiResponse(code = 500, message = "서버 내부 오류입니다, 관리자에게 문의 부탁드립니다.")
    })
    @GetMapping("/find-all")
    public ListResponse<Board> boardReadAll() {
        return responseService.getListResponse(boardService.findAll());
    }

    @GetMapping()
    @ApiOperation(value = "게시글 검색",notes = "검색 조건에 맞는 게시글을 조회하는 api")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "검색 키워드는 공백을 제외한 1글자 이상이어야 합니다."),
            @ApiResponse(code = 404, message = "존재하지 않는 게시글입니다."),
            @ApiResponse(code = 500, message = "서버 내부 오류입니다. 관리자에게 문의해주시기 바랍니다.")
    })
    public ListResponse<Board> search(@RequestParam("keyword") String keyword) {
        return responseService.getListResponse(boardService.searchFind(keyword));
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "특정 게시글 수정", notes = "특정 id의 게시글을 수정하는 API")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "존재하지 않는 게시글입니다."),
            @ApiResponse(code = 500, message = "서버 내부 오류입니다. 관리자에게 문의해주시기 바랍니다.")
    })
    public SingleResponse<Board> boardModify(@PathVariable("id") Long id,
                                             @RequestBody @Valid ModifyRequestDto modifyRequestDto) throws InvalidateBoardException {
        Board board = boardService.modify(id,modifyRequestDto);
        return responseService.getSingleResponse(board);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "특정 게시글 삭제", notes = "특정 id의 게시글을 삭제하는 API")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 404, message = "존재하지 않는 게시글입니다."),
            @ApiResponse(code = 500, message = "서버 내부 오류입니다. 관리자에게 문의해주시기 바랍니다.")
    })
    public ResponseEntity<String> boardDelete(@PathVariable("id") Long id) throws InvalidateBoardException {
        boardService.delete(id);
        return ResponseEntity.ok().body("ok");
    }

}
