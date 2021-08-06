package com.board.repository;

import com.board.domain.board.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public Long save(Board board){
        em.persist(board);
        return board.getId();
    }

    public Board find(Long id) {
        return em.find(Board.class, id);
    }

    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class)
                .getResultList();
    }

    public void delete(Long id) {
        Board board = em.find(Board.class, id);
        em.remove(board);
    }

    public Board findOneWithReplies(Long id) {

        return em.createQuery("select b from Board b " +
                "join fetch b.replies r " +
                "join fetch b.member m" +
                " where b.id =: id", Board.class)
                .setParameter("id", id)
                .getResultList().get(0);
    }
}
