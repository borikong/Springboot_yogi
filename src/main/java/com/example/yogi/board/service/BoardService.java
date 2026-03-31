package com.example.yogi.board.service;

import com.example.yogi.board.entity.Board;

import java.util.List;

public interface BoardService {
    //게시판 리스트 취득
    List<Board> getBoardList();

    //게시글 상세정보
    Board getBoardDetail(int boardNo);

    //조회수 업데이트
    void addCount(int boardNo);

}
