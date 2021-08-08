package com.board.repository;

import com.board.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MemberRepository {

    @Autowired
    private EntityManager em;

    public Long save(Member member) {

        em.persist(member);
        return member.getId();
    }

    public Member find(Long saveId) {

        return em.find(Member.class,  saveId);
    }

    public Member findByUsernameAndPassword(String username, String password) {

        return (Member) em.createQuery("select m from Member m " +
                "where m.username =: username and " +
                "m.password =: password")
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList().get(0);
    }

    public void delete(Member member) {
        em.remove(member);
    }

}

