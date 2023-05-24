package com.example.boardproject.controller;

import com.example.boardproject.common.Exception;
import com.example.boardproject.exception.InvalidateBoardException;
import com.example.boardproject.response.CommonResponse;
import com.example.boardproject.response.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Api(tags = "예외처리 컨트롤러")
public class ExceptionController {

    private final ResponseService responseService;
    @ExceptionHandler(InvalidateBoardException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiOperation(value = "게시물 조회 예외처리",notes = "게시물이 존재하지 않을 때 발생하는 예외를 처리한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "예외처리 성공"),
            @ApiResponse(code = 404, message = "예외처리 실패"),
            @ApiResponse(code = 500, message = "서버 내부 오류입니다. 관리자에게 문의하세요.")
    })
    private CommonResponse inValidateBoardException(InvalidateBoardException e) {
        return responseService.getErrorResponse(Exception.INVALIDATE_BOARD.getCode(), Exception.INVALIDATE_BOARD.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiOperation(value = "로그인 예외처리", notes = "존재하지 않는 계정으로 로그인을 할 시 발생하는 예외를 처리한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "예외처리 성공"),
            @ApiResponse(code = 404, message = "예외처리 실패"),
            @ApiResponse(code = 500, message = "서버 내부 오류입니다. 관리자에게 문의하세요.")
    })
    private CommonResponse inValidateBoardException(IllegalArgumentException e) {
        return responseService.getErrorResponse(Exception.INVALIDATE_LOGIN.getCode(), Exception.INVALIDATE_LOGIN.getMessage());
    }

}
