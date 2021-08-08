package com.board.controller;

import com.board.domain.reply.Reply;
import com.board.domain.reply.dtos.ReplyListDto;
import com.board.domain.reply.dtos.ReplyRegisterDto;
import com.board.repository.ReplyRepository;
import com.board.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/api/v1/register/{boardId}")
    public Long register(@RequestBody ReplyRegisterDto reply, @PathVariable("boardId") Long boardId){
        return replyService.register(reply.toEntity(), boardId);
    }

    @GetMapping("/api/v1/list/{boardId}")
    public ReplyListDtoWrapper getList(@PathVariable("boardId") Long boardId){

        List<Reply> replies = replyService.getList(boardId);
        return new ReplyListDtoWrapper(replies);
    }

    @PostMapping("/api/v1/modify")
    public ReplyIdWrapper modify(@RequestBody ReplyRegisterDto dto){
        Long id = replyService.modify(dto.toEntity());
        return new ReplyIdWrapper(id);
    }

    @DeleteMapping("/api/v1/delete/{replyId}")
    public void delete(@PathVariable("replyId") Long replyId){

        replyService.delete(replyId);
    }


    /**
     * 내부 클래스
     */
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static class ReplyIdWrapper{
        private Long id;
    }

    @Getter
    @NoArgsConstructor
    static class ReplyListDtoWrapper{

        private List<ReplyListDto> replyListDtos;

        public ReplyListDtoWrapper(List<Reply> replyListDtos) {
            List<ReplyListDto> collect = replyListDtos.stream().map(
                    reply -> ReplyListDto.toDto(reply)
            ).collect(Collectors.toList());

            this.replyListDtos = collect;
        }
    }


}
