package com.board.controller;

import com.board.domain.member.Member;
import com.board.domain.member.dtos.MemberFormDto;
import com.board.domain.member.dtos.MemberGetDto;
import com.board.repository.MemberRepository;
import com.board.service.MemberService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/api/v1/signup")
    public MemberIdWrapper signup(@RequestBody MemberFormDto dto){
        Member member =dto.toEntity();
        Long id = memberService.signup(member);
        return new MemberIdWrapper(id);
    }

    @PostMapping("/api/v1/login")
    public MemberBooleanWrapper login(@RequestBody MemberFormDto dto){
        Member member =dto.toEntity();
        Boolean isLogin = memberService.login(member);
        return new MemberBooleanWrapper(isLogin);
    }

    @GetMapping("/api/v1/get/{memberId}")
    public MemberGetDto get(@PathVariable("memberId") Long memberId){
        Member member = memberRepository.find(memberId);
        return MemberGetDto.toDto(member);
    }

    /**
     * static classes
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    static class MemberIdWrapper{

        private Long id;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    static class MemberBooleanWrapper{

        private Boolean is;
    }
}
