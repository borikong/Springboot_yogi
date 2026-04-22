package com.example.yogi.board.controller;

import com.example.yogi.board.dto.BoardDetailResponse;
import com.example.yogi.board.dto.BoardRequest;
import com.example.yogi.board.dto.BoardListResponse;
import com.example.yogi.board.entity.Board;
import com.example.yogi.board.service.BoardService;
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
    private final int PAGE_SIZE=10;         //한 페이지당 표시되는 게시글수

    //게시판 리스트
    @GetMapping("/boardlist")
    public String index(Model model){
        BoardListResponse response=new BoardListResponse();
        List<Board> boardList = boardService.getBoardList();
        //페이지당 PAGE_SIZE만큼만 표시
        List<Board> subList = boardList.subList(0, Math.min(PAGE_SIZE, boardList.size()));

        int i=0;
        for(Board board:subList){
            board.setTempNo(++i);
        }

        response.setCount(boardList.size());
        response.setBoardList(subList);
        model.addAttribute("board",response);
        return "board/boardlist";
    }

    //게시판 글 상세
    @GetMapping("/board/detail")
    public String detail(Model model, @RequestParam int no){
        boardService.addCount(no); //조회수 업데이트

        model.addAttribute("board",getBoardDetailResponse(no));
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

        model.addAttribute("board",getBoardDetailResponse(no));
        model.addAttribute("mode","EDIT");
        return "board/boardnew";
    }

    //글 등록,수정
    @PostMapping("/board/write")
    public String writeBoard(Model model, BoardRequest request){
        Board board = boardService.getBoardDetail(boardService.write(request));
        model.addAttribute("board",new BoardDetailResponse(board));
        return "board/boarddetail";
    }

    //글 삭제 폼으로
    @GetMapping("/board/delete")
    public String deleteBoard(Model model, @RequestParam int no){
        model.addAttribute("board",getBoardDetailResponse(no));
        return "board/boarddelete";
    }

    //글 삭제
    @PostMapping("/board/delete/proc")
    public String deleteBoardProc(Model model, @RequestParam int no){
        boardService.delete(no);
        BoardListResponse response=new BoardListResponse();
        List<Board> boardList = boardService.getBoardList();
        response.setCount(boardList.size());
        response.setBoardList(boardList);
        model.addAttribute("board",response);
        return "board/boardlist";
    }

    //글 번호로 글 정보 취득후 response객체로 반환
    private BoardDetailResponse getBoardDetailResponse(int no){
        return new BoardDetailResponse(boardService.getBoardDetail(no));
    }

}
