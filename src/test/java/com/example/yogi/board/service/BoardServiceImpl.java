package com.example.yogi.board.service;

import com.example.yogi.board.dto.BoardRequest;
import com.example.yogi.board.entity.Board;
import com.example.yogi.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    //게시글 리스트(작성일 기준 내림차순)
    @Override
    public List<Board> getBoardList() {
        return boardRepository.findAll(
                Sort.by(Sort.Direction.DESC, "regdate")
        );
    }

    //게시글 상세
    @Override
    public Board getBoardDetail(int boardNo) {
        return boardRepository.findById(boardNo).orElseThrow();
    }

    //조회수 업데이트
    @Override
    public void addCount(int boardNo) {
        Board board = boardRepository.findById(boardNo).orElseThrow();
        board.setReadcount(board.getReadcount()+1);
        boardRepository.save(board);
    }

    //게시글 작성, 수정
    @Override
    public int write(BoardRequest request) {
        Board board;
        //수정
        if(request.getMode().equals("EDIT")){
            board=Board.update(boardRepository.findById(request.getNo()).orElseThrow(),request);
        }else{
            board = Board.create(request);
        }

        return boardRepository.save(board).getNo();
    }

    @Override
    public void delete(int boardNo) {
        boardRepository.deleteById(boardNo);
    }

}
