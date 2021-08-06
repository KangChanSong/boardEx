package com.board.controller;

import com.board.domain.board.Board;
import com.board.domain.board.dtos.BoardListDto;
import com.board.domain.board.dtos.BoardReadDto;
import com.board.methods.BoardTestMethods;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;

import java.util.ArrayList;

import static com.board.methods.BoardTestMethods.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String LOCALHOST = "http://localhost:" ;

    @Test
    public void 게시판_등록(){

        //given
        String url = createUrl() + "/register";
        Board board = createBoard();

        //when
        ResponseEntity<BoardController.BoardId> responseEntity = 
                restTemplate.postForEntity(url, board, BoardController.BoardId.class);

        //then
        assertSame(responseEntity.getBody().getId(), 1L);
    }

    @Test
    public void 게시판_조회(){
        //given
        게시판_등록();

        String url  = createUrl() + "/get/1";

        //when
        ResponseEntity<BoardReadDto> responseEntity = restTemplate.getForEntity(url, BoardReadDto.class);
        BoardReadDto body = responseEntity.getBody();

        //then
        assertEquals(body.getAuthor(), BOARD_AUTHOR);
        assertEquals(body.getContent(), BOARD_CONTENT);
        assertEquals(body.getTitle(), BOARD_TITLE);
    }

    @Test
    public void 게시판_목록_조회(){
        //given
        String url = createUrl() + "/list";
        //when
        ResponseEntity<BoardController.BoardListWrapper> responseEntity = restTemplate.getForEntity(url, BoardController.BoardListWrapper.class);

        BoardController.BoardListWrapper body = responseEntity.getBody();

        //then
        assertEquals(body.getBoardListDtos().size(), 10);
    }


    String createUrl(){
        return LOCALHOST + port + "/board/api/v1";
    }
}