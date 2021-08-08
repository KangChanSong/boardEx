package com.board.domain.member.dtos;

import com.board.domain.member.Member;
import lombok.Data;

@Data
public class MemberFormDto {

    private String username;
    private String password;

    public Member toEntity() {

        return Member.builder()
                .username(this.username)
                .password(this.password)
                .build();
    }
}
