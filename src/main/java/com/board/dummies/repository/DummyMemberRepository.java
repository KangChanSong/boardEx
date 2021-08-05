package com.board.dummies.repository;

import com.board.dummies.domain.DummyMember;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class DummyMemberRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(DummyMember member){
        em.persist(member);
        return member.getId();
    }

    public DummyMember find(Long id){
        return em.find(DummyMember.class, id);
    }
}
