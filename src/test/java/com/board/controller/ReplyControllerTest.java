package com.board.controller;

import com.board.domain.reply.dtos.ReplyListDto;
import com.board.domain.reply.dtos.ReplyRegisterDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.board.test.methods.ReplyTestMethods.REPLY_CONTENT;
import static com.board.test.methods.ReplyTestMethods.REPLY_WRITER;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ReplyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    EntityManager em;

    @BeforeEach
    public void deleteData(){

        MockReplyRepository mockReplyRepository = new MockReplyRepository(em);
        mockReplyRepository.deleteAll();
//        assertEquals(getReplyList(1L).size() , 0);
    }


    @Test
    public void 댓글_등록(){
        //given
        Long boardId = 1L;
        registerReply(boardId);
        //when
        List<ReplyListDto> resultList = getReplyList(boardId);
        //then

        assertEquals(resultList.size(), 1);
        assertEquals(resultList.get(0).getContent(), REPLY_CONTENT);
        assertEquals(resultList.get(0).getWriter(), REPLY_WRITER);

    }

    @Test
    public void 댓글_수정(){

        //given
        Long boardId = 1L;
        Long replyId = registerReply(boardId);

        String modifiedContent = "modified";

        String modifyUrl = createUrl() + "/modify";
        ReplyRegisterDto dto = new ReplyRegisterDto();
        dto.setContent(modifiedContent);
        dto.setId(replyId);

        //when
        Long responseId =
                restTemplate.postForEntity(modifyUrl, dto, ReplyController.ReplyIdWrapper.class).getBody().getId();

        System.out.println("responseId == replyId -> " + (responseId == replyId));
        //then
        ReplyListDto resultDto = getReplyList(boardId).get(0);

        assertEquals(resultDto.getContent(), modifiedContent);

    }

    @Test
    public void 댓글_삭제(){
        //given
        Long boardId = 1L;
        Long replyId = registerReply(boardId);
        String deleteUrl = createUrl() + "/delete/" + replyId;

        //when
        restTemplate.delete(deleteUrl);

        //then

        assertEquals(getReplyList(boardId).size(), 0);

    }

    /**
     * 메서드
     */
    String createUrl(){
        return "http://localhost:" + port + "/reply/api/v1";
    }


    private Long registerReply(Long boardId) {
        String registerUrl = createUrl() + "/register/" + boardId;

        ReplyRegisterDto reply = new ReplyRegisterDto();
        reply.setWriter(REPLY_WRITER);
        reply.setContent(REPLY_CONTENT);

        //when
         return restTemplate.postForEntity(
                 registerUrl, reply, ReplyController.ReplyIdWrapper.class
         ).getBody().getId();

    }

    public List<ReplyListDto> getReplyList(Long boardId){
        String getListUrl = createUrl() + "/list/" + boardId;

        ResponseEntity<ReplyController.ReplyListDtoWrapper> result =
                restTemplate.getForEntity(getListUrl, ReplyController.ReplyListDtoWrapper.class);

        return result.getBody().getReplyListDtos();
    }
    /**
     * Mock 클래스
     */
    static class MockReplyRepository{

        EntityManager em;

        public MockReplyRepository(EntityManager em) {
            this.em = em;
        }

        public void deleteAll(){
            int result = em.createQuery("delete from Reply r").executeUpdate();
            System.out.println("result =========> " + result);
        }
    }

}
