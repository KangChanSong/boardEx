package com.board.service;

import com.board.domain.member.Member;
import com.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long signup(Member member){
        return memberRepository.save(member);
    }

    @Transactional
    public Boolean login(Member member){
        Member findMemer = memberRepository.findByUsernameAndPassword(
                member.getUsername(), member.getPassword());
        log.info("login => " + findMemer.toString());
        return findMemer == null ? false : true;
    }

    @Transactional
    public Long modify(Member member){
        Member findMember = memberRepository.find(member.getId());
        findMember.update(member);
        return findMember.getId();
    }

    @Transactional
    public void remove(Member member){
        memberRepository.delete(member);
    }
}
