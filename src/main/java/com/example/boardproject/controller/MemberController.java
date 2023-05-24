package com.example.boardproject.controller;
import java.util.Map;
import java.util.UUID;

import com.example.boardproject.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
@Api(tags = "회원가입 및 로그인 컨트롤러")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @ApiOperation(value = "회원가입 기능", notes = "회원가입을 당당하는 컨트롤러")
    @ApiResponses({
            @ApiResponse(code = 200,message = "회원가입 성공"),
            @ApiResponse(code = 404,message = "회원가입 실패"),
            @ApiResponse(code = 500,message = "서버 내부 오류입니다. 관리자에게 문의바랍니다.")
    })
    @PostMapping("/join")
    public ResponseEntity<UUID> register(@RequestBody Map<String, String> user) {
        return ResponseEntity.ok().body(memberService.join(user));
    }

    // 로그인
    @ApiOperation(value = "로그인 기능", notes = "로그인을 당당하는 컨트롤러")
    @ApiResponses({
            @ApiResponse(code = 200,message = "로그인 성공"),
            @ApiResponse(code = 404,message = "로그인 실패"),
            @ApiResponse(code = 500,message = "서버 내부 오류입니다. 관리자에게 문의바랍니다.")
    })
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        return memberService.login(user);
    }
}