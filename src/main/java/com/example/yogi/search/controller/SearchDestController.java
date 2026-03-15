package com.example.yogi.search.controller;

import com.example.yogi.member.service.MemberService;
import com.example.yogi.search.dto.DestinationRequest;
import com.example.yogi.search.dto.DestinationResponse;
import com.example.yogi.search.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchDestController {

    private final DestinationService destinationService;
    private final MemberService memberService;

    @RequestMapping({"/searchdest","/searchdest/index"})
    public String index(Model model,DestinationRequest request){
        model.addAttribute("request",request);

        return "searchdest/searchdest";
    }

    @RequestMapping("/searchdest/search")
    public String searchTest(Model model, DestinationRequest request){
        if(null!=request.getLoginId()){
            model.addAttribute("likeList", memberService.findUserLikeById(request.getLoginId()));
        }else{
            model.addAttribute("likeList", new ArrayList<String>());
        }
        model.addAttribute("destlist", destinationService.searchDestByKeyword(request));
        model.addAttribute("request",request);

        return "searchdest/searchdest";
    }

    @RequestMapping("/searchdest/searchpri")
    public String searchTest2(Model model, DestinationRequest request){
        if(null!=request.getLoginId()){
            model.addAttribute("likeList", memberService.findUserLikeById(request.getLoginId()));
        }else{
            model.addAttribute("likeList", new ArrayList<String>());
        }
        model.addAttribute("destlist", destinationService.searchDestByKeyword(request));
        model.addAttribute("request",request);

        return "searchdest/searchdest";
    }
}
