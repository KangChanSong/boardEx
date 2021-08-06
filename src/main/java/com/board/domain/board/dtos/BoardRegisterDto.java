package com.board.domain.board.dtos;

import com.board.domain.board.Board;
import lombok.Data;

@Data
public class BoardRegisterDto {

    private String author;
    private String title;
    private String content;

    public Board toEntity(){
        return Board.builder()
                .author(this.author)
                .title(this.title)
                .content(this.content)
                .build();
    }
}
