package com.example.yogi.board.entity;

import com.example.yogi.board.dto.BoardRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;						// 번호
    private String title;				// 제목
    private String writer;			    // 작성자
    private String pass;				// 비밀번호
    @Setter
    private int readcount;			    // 조회수
    private int ref;					// 기본댓글
    private int step;					// 댓글에 답할 때 밑에 옆으로 옮기는 간격
    private int depth;				    // 댓글에 답할 때 밑에 내리는 간격
    @CreationTimestamp
    private Timestamp regdate;	        // 등록날짜
    @Column(columnDefinition = "TEXT")
    private String content;			    // 본문

    public static Board create(BoardRequest request) {
        Board board = new Board();
        board.title = request.getTitle();
        board.pass = request.getPass();
        board.content = request.getContent();
        board.writer = request.getWriter();
        return board;
    }

    public static Board update(Board board,BoardRequest request) {
        board.title = request.getTitle();
        board.content = request.getContent();
        return board;
    }
}
