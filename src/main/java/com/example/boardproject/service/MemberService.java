package com.example.boardproject.service;

import com.example.boardproject.config.security.JwtTokenProvider;
import com.example.boardproject.domain.Member;
import com.example.boardproject.domain.Role;
import com.example.boardproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public Long join(Map<String,String> user) {
        Long id = memberRepository.save(Member.builder()
                        .email(user.get("email"))
                        .password(passwordEncoder.encode(user.get("password")))
                        .nickname(user.get("nickname"))
                        .phone(user.get("phone"))
                        .role(Role.ROLE_MEMBER).build()).getId();
        return id;
    }

    public String login(Map<String, String> user) {
        Member member = memberRepository.findByEmail(user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일 입니다."));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())){
            throw new IllegalArgumentException("비밀번호가 정확하지 않습니다.");
        }
        return jwtTokenProvider.createToken(member.getEmail(),member.getRole());
    }
}
