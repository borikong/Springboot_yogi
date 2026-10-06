package com.example.yogi.search.controller;

import com.example.yogi.member.service.MemberService;
import com.example.yogi.search.dto.*;
import com.example.yogi.search.entity.Destination;
import com.example.yogi.search.service.DestinationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DestinationController {

    private final DestinationService destinationService;
    private final MemberService memberService;
    private static final String ACTION_PRIORITY="priSearch";

    //초기화면, 여행지 검색
    @RequestMapping({"/searchdest","/searchdest/index","/searchdest/search"})
    public String index(Model model, DestinationRequest request,
                        @RequestParam(required = false) String action,
                        HttpSession session){
        DestinationResponse response = new DestinationResponse();
        String loginId=(String)session.getAttribute("loginID");
        //관심 여행지 목록 취득
        response.setLikeList(memberService.findUserLikeById(loginId));

        List<Destination> destList;

        if(ACTION_PRIORITY.equals(action)){
            //우선순위 검색
            destList=destinationService.searchDestByPriority(request);
        }else{
            //일반 검색
            destList=destinationService.searchDestByKeyword(request);
        }

        //response 리스트 반환
        response.setDestList(destList.stream().map(DestItemResponse::new).toList());
        model.addAttribute("response", response);
        model.addAttribute("request", request);

        return "searchdest/searchdest";
    }

    //여행지 상세
    @GetMapping("/searchdest/detail")
    public String index(Model model, DestDetailRequest request, HttpSession session){
        Destination destination = destinationService.getDestDetail(request);
        DestDetailResponse response=new DestDetailResponse(destination);

        String id =(String) session.getAttribute("loginID");
        if(null!=id){
            response.setLiked(memberService.isLike(id,request.getDestId()));
        }

        model.addAttribute("detail",response);
        return "searchdest/detailview";
    }
}
