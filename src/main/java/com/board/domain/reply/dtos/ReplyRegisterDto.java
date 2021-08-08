package com.board.domain.reply.dtos;

import com.board.domain.reply.Reply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyRegisterDto {

    private Long id;
    private String writer;
    private String content;

    public Reply toEntity(){
        return Reply.builder()
                .id(this.id)
                .writer(this.writer)
                .content(this.content)
                .build();
    }
}
