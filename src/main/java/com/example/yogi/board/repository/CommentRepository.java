package com.example.yogi.board.repository;

import com.example.yogi.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    // 특정 게시글(board_no)에 달린 댓글 목록 조회
    List<Comment> findByBoardNo(int boardNo);

}
