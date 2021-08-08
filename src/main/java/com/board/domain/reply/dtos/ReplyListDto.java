package com.board.domain.reply.dtos;

import com.board.domain.reply.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyListDto {

    private String writer;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static ReplyListDto toDto(Reply reply){
        return ReplyListDto.builder()
                .writer(reply.getWriter())
                .content(reply.getContent())
                .createdDate(reply.getCreatedDate())
                .modifiedDate(reply.getModifiedDate())
                .build();
    }
}
