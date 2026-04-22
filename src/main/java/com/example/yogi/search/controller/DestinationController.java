package com.example.yogi.search.controller;

import com.example.yogi.member.service.MemberService;
import com.example.yogi.search.dto.DestDetailRequest;
import com.example.yogi.search.dto.DestDetailResponse;
import com.example.yogi.search.dto.DestinationRequest;
import com.example.yogi.search.dto.DestinationResponse;
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

    @RequestMapping({"/searchdest","/searchdest/index","/searchdest/search"})
    public String index(Model model, DestinationRequest request,
                        @RequestParam(required = false) String action,
                        HttpSession session){
        model.addAttribute("request",request);

        //관심 여행지 목록 취득
        setLikeList(model,(String)session.getAttribute("loginID"));

        List<Destination> destList;

        if(ACTION_PRIORITY.equals(action)){
            //우선순위 검색
            destList=destinationService.searchDestByPriority(request);
        }else{
            //일반 검색
            destList=destinationService.searchDestByKeyword(request);
        }

        model.addAttribute("destlist",destList.stream().map(DestinationResponse::new).toList());
        model.addAttribute("request",request);

        return "searchdest/searchdest";
    }

    private void setLikeList(Model model, String loginId) {
        if (loginId != null) {
            model.addAttribute("likeList", memberService.findUserLikeById(loginId));
        } else {
            model.addAttribute("likeList", new ArrayList<>());
        }
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
