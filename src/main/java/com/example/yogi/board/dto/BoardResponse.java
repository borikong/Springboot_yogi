package com.example.yogi.board.dto;

import com.example.yogi.board.entity.Board;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardResponse {
    private int count;
    private List<Board> boardList;
}
