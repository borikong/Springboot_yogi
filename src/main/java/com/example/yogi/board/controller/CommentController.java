package com.example.yogi.board.controller;

import com.example.yogi.board.dto.BoardDetailResponse;
import com.example.yogi.board.dto.CommentRequest;
import com.example.yogi.board.entity.Comment;
import com.example.yogi.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add")
    public String add(CommentRequest request, RedirectAttributes redirectAttributes){
        try {
            commentService.addComment(request);
        }catch (IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/board/detail?no=" + request.getBoard_no();
    }

    @PostMapping("/edit")
    public String edit(Model model, CommentRequest request, RedirectAttributes redirectAttributes){
        try {
            commentService.editComment(request);
        }catch (IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/board/detail?no=" + request.getBoard_no();
    }

    @PostMapping("/delete")
    public  String delete(@RequestParam int cno, @RequestParam int boardNo){

        commentService.deleteComment(cno);
        return "redirect:/board/detail?no=" + boardNo;
    }
}
