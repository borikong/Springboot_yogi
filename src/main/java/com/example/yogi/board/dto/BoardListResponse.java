package com.example.yogi.board.dto;

import com.example.yogi.board.entity.Board;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class BoardListResponse {
    private int count; //0:게시글 없음
    private List<Board> boardList;
    private Page<Board> boardPage;
    private int startPage;
    private int endPage;
}
