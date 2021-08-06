package com.board.methods;

import com.board.domain.Member;

public class MemberTestMethods {

    public static final String USERNAME = "chan";
    public static final String PASSWORD = "1234";

    public static Member createMember(){
        Member member = new Member();
        member.setUsername(USERNAME);
        member.setPassword(PASSWORD);

        return member;
    }

}
