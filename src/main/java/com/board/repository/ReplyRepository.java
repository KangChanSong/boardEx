package com.board.repository;

import com.board.domain.reply.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ReplyRepository {

    @Autowired
    private EntityManager em;

    public Long save(Reply reply) {
        em.persist(reply);
        return reply.getId();
    }

    public Reply find(Long id) {
        return em.find(Reply.class,id);
    }

    public void delete(Long saveId) {
        em.remove(em.find(Reply.class, saveId));
    }

}
