package com.board.domain.board.dtos;

import com.board.domain.board.Board;
import com.board.domain.reply.dtos.ReplyReadDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardReadDto {

    private String author;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<ReplyReadDto> replies;

    public static BoardReadDto toDto(Board board){

        List<ReplyReadDto> collect = board.getReplies().stream()
                .map(reply -> ReplyReadDto.toDto(reply))
                .collect(Collectors.toList());

        return BoardReadDto.builder()
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .modifiedDate(board.getModifiedDate())
                .replies(collect)
                .build();
    }
}
