package com.example.yogi.board.entity;

import com.example.yogi.board.dto.CommentRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Entity
@Table(name = "board_comment")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //자동 증가
    private int cno;                //댓글 번호

    @ManyToOne
    @JoinColumn(name = "board_no")
    private Board board;           //게시글 번호

    private String cid;             //댓글 작성자

    @Column(columnDefinition = "TEXT")
    private String content;         //댓글 내용

    @CreationTimestamp
    private Timestamp regdate;      //댓글 등록 날짜

    public static Comment create(CommentRequest request) {

        return Comment.builder()
                .board(Board.builder().no(request.getBoard_no()).build())
                .cid(request.getCid())
                .content(request.getContent())
                .build();
    }
}
