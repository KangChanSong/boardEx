package com.board.dummies.repository;

import com.board.dummies.domain.DummyMember;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    DummyMemberRepository memberRepository;

    @Test
    public void test(){
        //given
        DummyMember member = new DummyMember();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);
        DummyMember findMember = memberRepository.find(saveId);

        //then
        assertThat(saveId).isEqualTo(findMember.getId());
        assertThat(member.getUsername()).isEqualTo(findMember.getUsername());
        assertThat(member == findMember).isEqualTo(true);
    }


}