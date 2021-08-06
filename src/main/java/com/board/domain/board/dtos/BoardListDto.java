package com.board.domain.board.dtos;

import com.board.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardListDto {
    private String author;
    private String title;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static BoardListDto toDto(Board board){

        return BoardListDto.builder()
                .author(board.getAuthor())
                .title(board.getTitle())
                .createdDate(board.getCreatedDate())
                .modifiedDate(board.getModifiedDate())
                .build();
    }
}
