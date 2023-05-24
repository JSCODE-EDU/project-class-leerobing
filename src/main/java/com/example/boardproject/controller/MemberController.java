package com.example.boardproject.controller;
import java.util.Map;
import java.util.UUID;

import com.example.boardproject.common.JwtTokenProvider;
import com.example.boardproject.domain.Member;
import com.example.boardproject.domain.Role;
import com.example.boardproject.repository.MemberRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    // 회원가입
    @ApiOperation(value = "회원가입 기능", notes = "회원가입을 당당하는 컨트롤러")
    @ApiResponses({
            @ApiResponse(code = 200,message = "회원가입 성공"),
            @ApiResponse(code = 404,message = "회원가입 실패"),
            @ApiResponse(code = 500,message = "서버 내부 오류입니다. 관리자에게 문의바랍니다.")
    })
    @PostMapping("/join")
    public UUID register(@RequestBody Map<String, String> user) {
        return memberRepository.save(Member.builder()
                .email(user.get("email"))
                .password(passwordEncoder.encode(user.get("password")))
                .nickname(user.get("nickname"))
                .phone(user.get("phone"))
                .role(Role.ROLE_MEMBER)
                .build()).getId();
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
        Member member = memberRepository.findByEmail(user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입 되지 않은 이메일입니다."));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 맞지 않습니다.");
        }

        return jwtTokenProvider.createToken(member.getEmail(), member.getRole());
    }


}