package com.board.controller;

import com.board.domain.board.Board;
import com.board.domain.board.dtos.BoardReadDto;
import com.board.domain.board.dtos.BoardRegisterDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static com.board.test.methods.BoardTestMethods.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

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
        ResponseEntity<BoardController.BoardIdWrapper> responseEntity =
                restTemplate.postForEntity(url, board, BoardController.BoardIdWrapper.class);

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
    
    @Test
    public void 게시판_수정(){
        //given
        Long id = 1L;
        String modifiedTitle = "modified";
        String modifiedContent = "modified";
        BoardRegisterDto boardRegisterDto = new BoardRegisterDto();
        boardRegisterDto.setId(id);
        boardRegisterDto.setTitle(modifiedTitle);
        boardRegisterDto.setContent(modifiedContent);

        //when
        String modifyUrl = createUrl() +"/modify";

        restTemplate.postForEntity(modifyUrl, boardRegisterDto, BoardController.BoardIdWrapper.class);

        //then
        String getUrl = createUrl() + "/get/1";
        ResponseEntity<BoardReadDto> result = restTemplate.getForEntity(getUrl, BoardReadDto.class);
        BoardReadDto resultDto = result.getBody();

        assertEquals(resultDto.getTitle(), modifiedTitle);
        assertEquals(resultDto.getContent(), modifiedContent);
    }


    String createUrl(){
        return LOCALHOST + port + "/board/api/v1";
    }
}