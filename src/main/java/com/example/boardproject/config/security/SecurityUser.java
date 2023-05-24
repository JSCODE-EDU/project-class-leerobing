package com.example.boardproject.config.security;
import com.example.boardproject.domain.Member;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Getter
public class SecurityUser extends User {
    private Member member;

    public SecurityUser(Member member) {
        super(member.getId().toString(), member.getPassword(),
                AuthorityUtils.createAuthorityList(member.getRole().toString()));
        this.member = member;
    }


}