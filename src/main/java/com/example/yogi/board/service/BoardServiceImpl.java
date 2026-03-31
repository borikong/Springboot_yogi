package com.example.yogi.board.service;

import com.example.yogi.board.dto.BoardRequest;
import com.example.yogi.board.entity.Board;
import com.example.yogi.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    @Override
    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    @Override
    public Board getBoardDetail(int boardNo) {
        return boardRepository.findById(boardNo).orElseThrow();
    }

    @Override
    public void addCount(int boardNo) {
        Board board = boardRepository.findById(boardNo).orElseThrow();
        board.setReadcount(board.getReadcount()+1);
        boardRepository.save(board);
    }
}
