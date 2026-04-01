package com.example.yogi.board.controller;

import com.example.yogi.board.entity.Board;
import com.example.yogi.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService;

    //비밀번호 확인
    @PostMapping("/checkPassword")
    public boolean checkPassword(@RequestParam int boardNo, @RequestParam String pass){
        return boardService.getBoardDetail(boardNo).getPass().equals(pass);
    }
}
