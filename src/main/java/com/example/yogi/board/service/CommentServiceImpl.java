package com.example.yogi.board.service;

import com.example.yogi.board.dto.CommentRequest;
import com.example.yogi.board.entity.Board;
import com.example.yogi.board.entity.Comment;
import com.example.yogi.board.repository.BoardRepository;
import com.example.yogi.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    //boardNo로 댓글리스트 취득
    @Override
    public List<Comment> getCommentList(int boardNo) {
        return commentRepository.findByBoardNo(boardNo);
    }

    //댓글 작성
    @Override
    public int addComment(CommentRequest request) {
        Comment comment=Comment.create(request);
        return commentRepository.save(comment).getCno();
    }

    //댓글 수정
    @Override
    public String editComment(int boardNo, String cid, String content) {
        return "";
    }

    //댓글 삭제
    @Override
    public void deleteComment(int cno) {
        commentRepository.deleteById(cno);
    }
}
