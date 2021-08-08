package com.board.service;

import com.board.domain.reply.Reply;
import com.board.repository.BoardRepository;
import com.board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    /**
     * 등록
     */
    @Transactional
    public Long register(Reply reply, Long boardId){
        reply.setBoard(boardRepository.find(boardId));
        replyRepository.save(reply);
        System.out.println(reply.getId());
        return reply.getId();
    }

    /**
     * 목록조회
     */
    public List<Reply> getList(Long boardId){
        return replyRepository.findAllByBoardId(boardId);
    }

    /**
     * 수정
     */
    @Transactional
    public Long modify(Reply reply){
        Reply findReply = replyRepository.find(reply.getId());
        findReply.update(reply);

        return findReply.getId();
    }

    /**
     * 삭제
     */
    @Transactional
    public void delete(Long replyId){
        replyRepository.delete(replyId);
    }
}
