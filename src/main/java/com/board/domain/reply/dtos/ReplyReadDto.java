package com.board.domain.reply.dtos;

import com.board.domain.reply.Reply;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReplyReadDto {

    private String writer;
    private String content;
    private LocalDateTime createdDate;

    public static ReplyReadDto toDto(Reply reply){

        return ReplyReadDto.builder()
                .writer(reply.getWriter())
                .content(reply.getContent())
                .createdDate(reply.getCreatedDate())
                .build();
    }
}
