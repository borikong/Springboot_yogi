package com.example.yogi.board.controller;

import com.example.yogi.board.dto.BoardResponse;
import com.example.yogi.board.entity.Board;
import com.example.yogi.board.service.BoardService;
import com.example.yogi.search.dto.DestDetailRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/boardlist")
    public String index(Model model, DestDetailRequest request, HttpSession session){
        BoardResponse response=new BoardResponse();
        List<Board> boardList = boardService.getBoardList();
        response.setCount(boardList.size());
        response.setBoardList(boardList);
        model.addAttribute("board",response);
        return "board/boardlist";
    }

    @GetMapping("/board/detail")
    public String detail(Model model, @RequestParam int no){
//        BoardDetailResponse response=new BoardDetailResponse();
        boardService.addCount(no); //조회수 업데이트
        Board board = boardService.getBoardDetail(no);
        model.addAttribute("board",board);
        return "board/boarddetail";
    }

    @GetMapping("/board/new")
    public String newBoard(Model model, DestDetailRequest request, HttpSession session){

        return "board/boardnew";
    }
}
