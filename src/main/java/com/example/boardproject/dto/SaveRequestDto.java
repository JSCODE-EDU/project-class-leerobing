package com.example.boardproject.dto;

import com.example.boardproject.domain.Comment;
import com.example.boardproject.domain.Member;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
public class SaveRequestDto {

    @NotBlank(message = "제목은 필수로 입력해야 합니다.")
    @Size(min = 1, max = 15)
    private String title;

    @NotBlank(message = "내용은 필수로 입력해야 합니다.")
    @Size(min = 1, max = 1000)
    private String content;




}

