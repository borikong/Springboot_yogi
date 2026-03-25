package com.example.yogi.search.controller;

import com.example.yogi.member.service.MemberService;
import com.example.yogi.search.dto.DestinationRequest;
import com.example.yogi.search.service.DestinationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchDestController {

    private final DestinationService destinationService;
    private final MemberService memberService;
    private static final String ACTION_PRIORITY="priSearch";

    @RequestMapping({"/searchdest","/searchdest/index","/searchdest/search"})
    public String index(Model model, DestinationRequest request,
                        @RequestParam(required = false) String action,
                        HttpSession session){
        model.addAttribute("request",request);

        setLikeList(model,(String)session.getAttribute("loginID"));

        if(ACTION_PRIORITY.equals(action)){
            //우선순위 검색
            //일반 검색
            model.addAttribute("destlist", destinationService.searchDestByPriority(request));
            model.addAttribute("request",request);
        }else{
            //일반 검색
            model.addAttribute("destlist", destinationService.searchDestByKeyword(request));
            model.addAttribute("request",request);
        }

        return "searchdest/searchdest";
    }

    private void setLikeList(Model model, String loginId) {
        if (loginId != null) {
            model.addAttribute("likeList", memberService.findUserLikeById(loginId));
        } else {
            model.addAttribute("likeList", new ArrayList<>());
        }
    }
}
