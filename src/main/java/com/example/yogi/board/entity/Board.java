package com.example.yogi.board.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private Timestamp regdate;	        // 등록날짜
    @Column(columnDefinition = "TEXT")
    private String content;			    // 본문

}
