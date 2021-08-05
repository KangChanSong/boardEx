package com.board.dummies;

import com.board.domain.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class EntityManagerTest {

    @Autowired
    EntityManager em;

    /**
     *  영속성 컨텍스트에 해당 객체가 없을때
     *   예외를 터뜨리는지, 널을 반환하는지에 대한 테스트
     */
    @Test
    public void 엔티티매니저_예외(){

        Board board = em.find(Board.class, 1L);

        Assertions.assertThrows(NullPointerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                board.toString();
            }
        });
    }

    /**
     * 하이버네이트는 엔티티를 영속화 할 때, 컬랙션을 감싸서 하이버네이트가 제공하는 내장 컬렉션으로 변경한
     * 다
     */
    @Test
    public void WRAPPING_테스트(){

        Board board= new Board();
        System.out.println(board.getReplies().getClass());

        board.getReplies().forEach( System.out::println);
        em.persist(board);
        System.out.println(board.getReplies().getClass());
        board.getReplies().forEach( System.out::println);
    }
}
