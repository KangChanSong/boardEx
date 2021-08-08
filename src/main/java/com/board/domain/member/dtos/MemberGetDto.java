package com.board.domain.member.dtos;

import com.board.domain.member.Member;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberGetDto {

    private Long id;
    private String username;
    private String password;

    public static MemberGetDto toDto(Member member){
        return  MemberGetDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .password(member.getPassword())
                .build();
    }
}
