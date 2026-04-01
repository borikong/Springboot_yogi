package com.example.yogi.board.controller;

import com.example.yogi.board.dto.BoardRequest;
import com.example.yogi.board.dto.BoardResponse;
import com.example.yogi.board.entity.Board;
import com.example.yogi.board.service.BoardService;
import com.example.yogi.search.dto.DestDetailRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //게시판 리스트
    @GetMapping("/boardlist")
    public String index(Model model){
        BoardResponse response=new BoardResponse();
        List<Board> boardList = boardService.getBoardList();
        response.setCount(boardList.size());
        response.setBoardList(boardList);
        model.addAttribute("board",response);
        return "board/boardlist";
    }

    //게시판 글 상세
    @GetMapping("/board/detail")
    public String detail(Model model, @RequestParam int no){
//        BoardDetailResponse response=new BoardDetailResponse();
        boardService.addCount(no); //조회수 업데이트
        Board board = boardService.getBoardDetail(no);
        model.addAttribute("board",board);
        return "board/boarddetail";
    }

    //새글 작성 폼으로
    @GetMapping("/board/new")
    public String newBoard(){
        return "board/boardnew";
    }

    //글 수정 폼으로
    @GetMapping("/board/edit")
    public String editBoard(Model model, @RequestParam int no){
        Board board = boardService.getBoardDetail(no);
        model.addAttribute("board",board);
        model.addAttribute("mode","EDIT");
        return "board/boardnew";
    }

    //글 등록,수정
    @PostMapping("/board/write")
    public String writeBoard(Model model, BoardRequest request){
        Board board = boardService.getBoardDetail(boardService.write(request));
        model.addAttribute("board",board);
        return "board/boarddetail";
    }

    //글 삭제 폼으로
    @GetMapping("/board/delete")
    public String deleteBoard(Model model, @RequestParam int no){
        Board board = boardService.getBoardDetail(no);
        model.addAttribute("board",board);
        return "board/boarddelete";
    }

    //글 삭제
    @PostMapping("/board/delete/proc")
    public String deleteBoardProc(Model model, @RequestParam int no){
        boardService.delete(no);
        BoardResponse response=new BoardResponse();
        List<Board> boardList = boardService.getBoardList();
        response.setCount(boardList.size());
        response.setBoardList(boardList);
        model.addAttribute("board",response);
        return "board/boardlist";
    }

}
