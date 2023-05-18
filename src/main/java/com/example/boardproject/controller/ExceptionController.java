package com.example.boardproject.controller;

import com.example.boardproject.common.Exception;
import com.example.boardproject.exception.InvalidateBoardException;
import com.example.boardproject.response.CommonResponse;
import com.example.boardproject.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    private final ResponseService responseService;
    @ExceptionHandler(InvalidateBoardException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private CommonResponse inValidateBoardException(InvalidateBoardException e) {
        return responseService.getErrorResponse(Exception.INVALIDATE_BOARD.getCode(), Exception.INVALIDATE_BOARD.getMessage());
    }
}
