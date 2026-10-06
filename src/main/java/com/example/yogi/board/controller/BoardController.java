package com.example.yogi.board.controller;

import com.example.yogi.board.dto.BoardDetailResponse;
import com.example.yogi.board.dto.BoardListResponse;
import com.example.yogi.board.dto.BoardRequest;
import com.example.yogi.board.entity.Board;
import com.example.yogi.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    @GetMapping("/board/boardlist")
    public String index(
            @RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String keyword, //URL에서 keyword라는 값을 가져와서 String keyword 변수에 넣어라
            Model model) {      // @RequestParam(defaultValue = "0") int page 는 URL에 ?page=1 같은 값이 있으면 page에 1을 넣고, page가 없으면 기본값으로 0을 사용한다.

        BoardListResponse response = new BoardListResponse();

        Page<Board> boardPage;

        if (keyword == null || keyword.isBlank()) {
            // 검색어가 없으면 전체 게시글
            boardPage = boardService.getPagingBoardList(page, PAGE_SIZE);
        } else {
            // 검색어가 있으면 검색 결과
            boardPage = boardService.searchBoardList(keyword, page, PAGE_SIZE);
        }

        int totalPage = boardPage.getTotalPages();
        int currentPage = boardPage.getNumber();
        int pageBlock = 10;

        //현재 페이지가 속한 페이지 묶음의 시작 번호
        int startPage = (currentPage / pageBlock) * pageBlock;
        //그 묶음의 끝 번호. 단, 실제 전체 페이지보다 커지면 전체 페이지까지만
        int endPage = Math.min(startPage + pageBlock, totalPage);

        response.setCount((int) boardPage.getTotalElements());
        response.setBoardList(boardPage.getContent());
        response.setBoardPage(boardPage);
        response.setStartPage(startPage);
        response.setEndPage(endPage);

        model.addAttribute("board", response);
        model.addAttribute("keyword", keyword);

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
