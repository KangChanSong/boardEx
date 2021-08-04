package com.board.repository;

import com.board.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    /**
     * 등록
     */
    @Test
    public void 게시판_등록_조회(){
        //given
        String title = "제목";
        String content = "내용";
        String author = "저자";

        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setAuthor(author);

        //when
        boardRepository.save(board);
        Board findedBoard = boardRepository.find(board.getId());

        //then
        assertThat(findedBoard.getId()).isEqualTo(board.getId());
        assertThat(findedBoard.getTitle()).isEqualTo(title);
        assertThat(findedBoard.getContent()).isEqualTo(content);
        assertThat(findedBoard.getAuthor()).isEqualTo(author);
        assertThat(findedBoard == board).isEqualTo(true);
    }

    /**
     * 목록 조회
     */
    @Test
    public void 게시판_목록_조회(){
        //given
        Board b1 = new Board();
        Board b2 = new Board();
        Board b3 = new Board();

        //when
        boardRepository.save(b1);
        boardRepository.save(b2);
        boardRepository.save(b3);

        List<Board> boards = boardRepository.findAll();

        //then
        assertThat(boards.size()).isEqualTo(3);
    }

    /**
     * 수정
     */
    @Test
    public void 게시판_수정(){
        //given
        Board board = new Board();
        board.setTitle("title");
        boardRepository.save(board);

        //when
        /**
         * board 는 현재 영속성 컨텍스트 안에 있음
         * 영속성 컨텍스트 안에서 수정됨
         * 그래서 트랜잭션이 커밋되는 시점에 변경 감지가 일어나 db에 반영됏음
         */
        board.setTitle("title2");

        //then
        Board findedBoard = boardRepository.find(board.getId());
        System.out.println(findedBoard.getTitle());
    }

    /**
     * 삭제
     */
    @Test
    public void 게시판_삭제(){
        //given
        Board board = new Board();

        //when
        boardRepository.save(board);
        boardRepository.delete(board.getId());

        /**
         *  remove 는 영속성 컨텍스트 안의 객체를 삭제하는건가?
         *  어떻게 id를 여전히 가져올 수 있는거지
         */
        //then
        Board findedboard = boardRepository.find(board.getId());
        assertThat(findedboard).isNull();
    }
}