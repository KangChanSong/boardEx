package com.board.repository;

import com.board.domain.board.Board;
import com.board.domain.reply.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
@Transactional
public class ReplyRepositoryTest {

    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    BoardRepository boardRepository;

    /**
     * 게시판 생성 후
     * 불러와서 댓글 작성
     * 후 조회 테스트
     */
    @Test
    public void 댓글_등록(){
        //given
        Board board = new Board();
        //board.setContent("board");
        boardRepository.save(board);
        Board findBoard = boardRepository.find(board.getId());

        Reply reply = new Reply();
        reply.setContent("reply");
        reply.setBoard(findBoard);

        //when
        Long saveId = replyRepository.save(reply);
        Reply findReply = replyRepository.find(saveId);

        //then
        assertSame(findReply.getContent(), "reply");
        assertSame(findReply, findBoard.getReplies().get(0));

    }

    /**
     * 댓글 삭제
     */
    @Test
    public void 댓글_삭제(){
        //given
        Reply reply = new Reply();
        //when
        Long saveId = replyRepository.save(reply);
        replyRepository.delete(saveId);

        //then
        assertNull(replyRepository.find(saveId));
    }
}
