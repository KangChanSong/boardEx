package com.board.service;

import com.board.domain.board.Board;
import com.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Long register(Board board){
        return boardRepository.save(board);
    }

    public Board get(Long id){
        return boardRepository.find(id);
    }

    public List<Board> getList(){
        return boardRepository.findAll();
    }

    public void modify(Board board){

        Board findBoard = boardRepository.find(board.getId());
        findBoard.update(board);

    }

    public void delete(Long id){
        boardRepository.delete(id);
    }

}
