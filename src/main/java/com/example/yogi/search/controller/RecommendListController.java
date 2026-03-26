package com.example.yogi.search.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class RecommendListController {

    //추천리스트 페이지로
    @GetMapping("/recommend")
    public String index(Model model, HttpSession session){

        return "recommend/recommendlist";
    }
}
