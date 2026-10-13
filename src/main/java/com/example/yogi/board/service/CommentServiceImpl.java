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
        return commentRepository.findByBoardNoOrderByCnoDesc(boardNo);
    }

    //댓글 작성
    @Override
    public int addComment(CommentRequest request) {
        validateContent(request.getContent());
        Comment comment=Comment.create(request);
        return commentRepository.save(comment).getCno();
    }

    //댓글 수정
    @Override
    public int editComment(CommentRequest request) {
        validateContent(request.getContent());
        Comment comment = commentRepository.findById(request.getCno()).orElseThrow(() -> new IllegalArgumentException("댓글이 없습니다."));
        comment.setContent(request.getContent());
        return commentRepository.save(comment).getCno();
    }

    //댓글 삭제
    @Override
    public void deleteComment(int cno) {
        commentRepository.deleteById(cno);
    }

    //댓글 검증
    protected void validateContent(String content){
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("댓글 내용을 입력해주세요.");
        }
    }
}
