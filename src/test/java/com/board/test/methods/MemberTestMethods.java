package com.board.test.methods;

import com.board.domain.member.Member;

public class MemberTestMethods {

    public static final String USERNAME = "chan";
    public static final String PASSWORD = "1234";

    public static Member createMember(){
        return Member.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }

}
