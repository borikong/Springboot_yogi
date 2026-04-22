package com.example.yogi.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequest {
    private int no;                     // 글 번호
    private String title;				// 제목
    private String writer;			    // 작성자
    private String pass;				// 비밀번호
    private String content;			    // 본문
    private String mode;                // EDIT 수정 NEW 등록
    private int page;                   // 페이징
}
