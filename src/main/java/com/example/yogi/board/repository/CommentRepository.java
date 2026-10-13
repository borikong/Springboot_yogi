package com.example.yogi.board.repository;

import com.example.yogi.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findByBoardNoOrderByCnoDesc(int boardNo);

}
