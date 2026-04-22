package com.example.yogi.board.dto;

import com.example.yogi.board.entity.Board;
import jakarta.persistence.Column;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

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

    public BoardDetailResponse(){}
    public BoardDetailResponse(Board board){
        this.no=board.getNo();
        this.title=board.getTitle();
        this.writer=board.getWriter();
        this.readcount=board.getReadcount();
        this.ref=board.getRef();
        this.step=board.getStep();
        this.depth=board.getDepth();
        this.regdate=board.getRegdate();
        this.content=board.getContent();
    }
}
