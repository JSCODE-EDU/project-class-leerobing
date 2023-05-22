package com.example.boardproject.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Exception {

    INVALIDATE_BOARD(1000,"해당 게시물이 존재하지 않습니다."),
    INVALIDATE_LOGIN(2000,"계정 정보가 정확하지 않습니다.");


    private final int code;
    private final String message;


}
