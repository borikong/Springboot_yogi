package com.example.yogi.board.service;
import com.example.yogi.board.dto.CommentRequest;
import com.example.yogi.board.entity.Comment;
import com.example.yogi.board.repository.CommentRepository;

import java.util.List;

public interface CommentService {
    //boardNo로 댓글리스트 취득
    List<Comment> getCommentList(int boardNo);
    //댓글 작성
    int addComment(CommentRequest request);
    //댓글 수정
    String editComment(int boardNo, String cid, String content);
    //댓글 삭제
    void deleteComment(int cno);
}
