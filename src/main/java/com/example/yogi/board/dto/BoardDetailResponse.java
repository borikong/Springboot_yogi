package com.example.yogi.board.dto;

import com.example.yogi.board.entity.Board;
import com.example.yogi.board.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
public class BoardDetailResponse {
    private int no;						// 번호
    private String title;				// 제목
    private String writer;			    // 작성자
    private int readcount;			    // 조회수
    private int ref;					// 기본댓글
    private int step;					// 댓글에 답할 때 밑에 옆으로 옮기는 간격
    private int depth;				    // 댓글에 답할 때 밑에 내리는 간격
    private Timestamp regdate;	        // 등록날짜
    private String content;			    // 본문
    private int page;
    private String keyword;
    private String searchType;
    @Setter
    private List<Comment> commentList; //댓글 리스트

    public BoardDetailResponse(){}
    public BoardDetailResponse(Board board, int page, String keyword, String searchType){
        this.no=board.getNo();
        this.title=board.getTitle();
        this.writer=board.getWriter();
        this.readcount=board.getReadcount();
        this.ref=board.getRef();
        this.step=board.getStep();
        this.depth=board.getDepth();
        this.regdate=board.getRegdate();
        this.content=board.getContent();
        this.page=page;
        this.keyword=keyword;
        this.searchType=searchType;
    }
}
