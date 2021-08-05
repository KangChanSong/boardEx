package com.board.repository;

import com.board.domain.Board;
import com.board.domain.Member;
import com.board.domain.Reply;
import com.board.repository.methods.BoardTestMethods;
import com.board.repository.methods.MemberTestMethods;
import com.board.repository.methods.ReplyTestMethods;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.board.repository.methods.BoardTestMethods.BOARD_CONTENT;
import static com.board.repository.methods.BoardTestMethods.createBoard;
import static com.board.repository.methods.MemberTestMethods.USERNAME;
import static com.board.repository.methods.MemberTestMethods.createMember;
import static com.board.repository.methods.ReplyTestMethods.REPLY_CONTENT;
import static com.board.repository.methods.ReplyTestMethods.createReply;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
@Transactional
public class RepositoryIntegrationTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    ReplyRepository replyRepository;

    /**
     * 회원으로 게시판 작성후 그 게시판에 댓글 작성
     */
    @Test
    public void 작성(){

        //given
        Member member = createMember();
        Board board = createBoard();
        Reply reply  = createReply();

        //when
        memberRepository.save(member);

        board.setMember(member);
        boardRepository.save(board);

        reply.setBoard(board);
        reply.setMember(member);
        replyRepository.save(reply);

        //then
        Member findMember = memberRepository.find(member.getId());
        Board findBoard = boardRepository.find(board.getId());
        Reply findReply = replyRepository.find(reply.getId());

        assertSame(findMember, findBoard.getMember());
        assertSame(findMember, findReply.getMember());

        assertSame(findBoard, findReply.getBoard());

        assertSame(findMember.getUsername(), USERNAME);
        assertSame(findBoard.getContent(), BOARD_CONTENT);
        assertSame(findReply.getContent(), REPLY_CONTENT);
    }

    @Test
    public void 조회(){
        //given
        Member member = createMember();
        Board board = createBoard();
        Reply reply = createReply();

        //when
        memberRepository.save(member);
        board.setMember(member);
        boardRepository.save(board);
        reply.setMember(member);
        reply.setBoard(board);
        replyRepository.save(reply);

        //then
        List<Board> findedBoards = boardRepository.findAll();

        List<Reply> findedRepliesByBoard = replyRepository.findAllByBoardId(board.getId());


    }

}
