package com.example.yogi.board.service;

import com.example.yogi.board.dto.BoardRequest;
import com.example.yogi.board.entity.Board;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardService {
    //게시판 리스트 취득
    List<Board> getBoardList();

    //게시글 상세정보
    Board getBoardDetail(int boardNo);

    //조회수 업데이트
    void addCount(int boardNo);

    //게시글 작성
    int write(BoardRequest request);

    //게시글 삭제
    void delete(int boardNo);

    //페이징
    Page<Board> getPagingBoardList(int page, int pageSize);

    //게시판 모든 항목 검색
    Page<Board> findByAllContaining(String keyword, int page, int pageSize);
    //게시판 no 검색
    Page<Board> findByNoContaining(String keyword, int page, int pageSize);
    //게시판 제목 검색
    Page<Board> findByTitleContaining(String keyword, int page, int pageSize);
    //게시판 작성자 검색
    Page<Board> findByWriterContaining(String keyword, int page, int pageSize);
    //게시판 내용 검색
    Page<Board> findByContentContaining(String keyword, int page, int pageSize);
}
