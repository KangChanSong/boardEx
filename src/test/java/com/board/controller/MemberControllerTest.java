package com.board.controller;

import com.board.domain.member.Member;
import com.board.domain.member.dtos.MemberFormDto;
import com.board.domain.member.dtos.MemberGetDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static com.board.test.methods.MemberTestMethods.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 회원_등록(){
        //given
        MemberFormDto dto = new MemberFormDto();
        dto.setUsername(USERNAME);
        dto.setPassword(PASSWORD);

        String loginUrl = createUrl() + "login";
        String signupUrl = createUrl() + "signup";

        //when
        restTemplate.postForEntity(signupUrl, dto , null);

        //then
        Boolean isLogin = restTemplate.postForEntity(loginUrl, dto, MemberController.MemberBooleanWrapper.class).getBody().getIs();

        assertTrue(isLogin);

    }

    private String createUrl(){
        return "http://localhost:" + this.port + "/member/api/v1/";
    }

}