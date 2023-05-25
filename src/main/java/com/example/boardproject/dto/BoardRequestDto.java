package com.example.boardproject.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
public class BoardRequestDto {

    private long id;

    @NotBlank(message = "제목은 필수로 입력해야 합니다.")
    private String title;

    @NotBlank(message = "내용은 필수로 입력해야 합니다.")
    private String content;

}
