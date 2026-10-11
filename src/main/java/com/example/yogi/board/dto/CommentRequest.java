package com.example.yogi.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private int board_no;
    private  String cid;
    private  String content;
}
