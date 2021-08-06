package com.board.dummies.repository;

import com.board.domain.board.Board;
import com.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDummiesRepository {

    private final InitBoardService initBoardService;

    @PostConstruct
    public void insertDummies(){
        getDummies().forEach(
                i -> initBoardService.insertDummy(i)
        );
    }

    List<Board> getDummies() {
        List<Board> boards = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            boards.add(
                    Board.builder()
                            .author("a" + i)
                            .title("t" + i)
                            .content("c" + i)
                            .build());
        }
        return boards;
    }

    @Service
    @Transactional
    @RequiredArgsConstructor
    static class InitBoardService{
        private final EntityManager em;

        public void insertDummy(Board board){
            em.persist(board);
        }
    }
}
