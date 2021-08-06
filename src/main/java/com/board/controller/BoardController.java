package com.board.controller;

import com.board.domain.board.Board;
import com.board.domain.board.dtos.BoardListDto;
import com.board.domain.board.dtos.BoardReadDto;
import com.board.domain.board.dtos.BoardRegisterDto;
import com.board.repository.BoardRepository;
import com.board.service.BoardService;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("api/v1/get/{id}")
    public BoardReadDto get(@PathVariable("id") Long id){
        return BoardReadDto.toDto(boardService.get(id));
    }

    @PostMapping("api/v1/register")
    public BoardId register(@RequestBody BoardRegisterDto dto){

        Long saveId = boardService.register(dto.toEntity());
        System.out.println("======controller=======");
        return new BoardId(saveId);
    }

    @GetMapping("api/v1/list")
    public BoardListWrapper getList(){
        return new BoardListWrapper(boardService.getList());
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    static class BoardId{
        private Long id;

    }

    @NoArgsConstructor
    @Getter
    static class BoardListWrapper{

        private List<BoardListDto> boardListDtos;

        public BoardListWrapper(List<Board> boardListDtos) {
            List<BoardListDto> collect = boardListDtos.stream().map(board -> BoardListDto.toDto(board))
                    .collect(Collectors.toList());

            this.boardListDtos = collect;
        }
    }
}
