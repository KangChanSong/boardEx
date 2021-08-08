package com.board.service;

import com.board.domain.board.Board;
import com.board.domain.reply.Reply;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static com.board.test.methods.BoardTestMethods.createBoard;
import static com.board.test.methods.ReplyTestMethods.REPLY_CONTENT;
import static com.board.test.methods.ReplyTestMethods.REPLY_WRITER;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private EntityManager em;

    private MockBoardRepository mockBoardRepository;
    @Before
    public void inject(){

        mockBoardRepository = new MockBoardRepository(em);
        Board board = createBoard();
        mockBoardRepository.save(board);
    }

    @Test
    public void 댓글_등록(){
        //given
        Long boardId = 1L;
        Reply reply = Reply.builder()
                .writer(REPLY_WRITER)
                .content(REPLY_CONTENT)
                .build();
        //when
        Long saveId = replyService.register(reply, boardId);

        //then
        List<Reply> list = replyService.getList(boardId);

        System.out.println("=========> "  + list.get(0).getContent());
        //save 하면 트랜잭션이 끝나고
        //그다음 트랜잭션에서 조회하기 때문에 동일하지는 않음
        //assertSame(list.get(0), reply);

        assertEquals(list.get(0).getContent(), reply.getContent());
    }

    @Repository
    static class MockBoardRepository{

        EntityManager em;

        public MockBoardRepository(EntityManager em) {
            this.em = em;
        }

        public MockBoardRepository(){}

        public Long save(Board board){
            em.persist(board);
            return board.getId();
        }
    }
}