package com.board.repository;

import com.board.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static com.board.test.methods.MemberTestMethods.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    
    @Test
    public void 회원_가입(){
        //given
        Member member = createMember();
        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        assertSame(findMember, member);
        assertSame(findMember.getUsername(), USERNAME);
    }
    
    @Test
    public void 로그인(){

        //given

        Member member = createMember();
        //when
        memberRepository.save(member);
        Member findMember = memberRepository.findByUsernameAndPassword(USERNAME, PASSWORD);
        
        //then
        assertSame(findMember.getUsername() , USERNAME);
        assertSame(findMember.getPassword(), PASSWORD);
        assertSame(findMember.getId(), member.getId());
        assertSame(findMember, member);
    }
    
    @Test
    public void 회원_정보_수정(){
        //given
        Member member = createMember();

        //when
        memberRepository.save(member);
        //member.setUsername("수정됨");

        //then
        Member findMember = memberRepository.find(member.getId());
        assertSame(findMember.getUsername(), "수정됨");
    }

    @Test
    public void 회원_정보_삭제(){
        //given
        Member member = createMember();

        //when
        Long saveId = memberRepository.save(member);
        memberRepository.delete(member);

        //then
        assertNull(memberRepository.find(saveId));
    }


}