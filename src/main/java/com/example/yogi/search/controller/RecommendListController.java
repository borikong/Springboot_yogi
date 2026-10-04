package com.example.yogi.search.controller;

import com.example.yogi.member.service.MemberService;
import com.example.yogi.search.dto.RecommendRequest;
import com.example.yogi.search.dto.RecommendResponse;
import com.example.yogi.search.entity.Destination;
import com.example.yogi.search.service.RecommendService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RecommendListController {
    private final RecommendService recommendService;
    private final MemberService memberService;

    //추천리스트 페이지로
    @GetMapping("/recommend")
    public String index(Model model, HttpSession session){
        RecommendResponse response=new RecommendResponse();
        String  loginID = (String)session.getAttribute("loginID");
        //로그인 세션 만료 처리
        if(loginID==null || loginID.isEmpty()){
            return "member/login";
        }

        //좋아요 표시 목록이 없는 경우
        if(memberService.findUserLikeById(loginID).isEmpty()){
            response.setMode(RecommendResponse.Mode.EMPTY_FAVORITE);
        }else{
            response.setMode(RecommendResponse.Mode.HAS_RECOMMEND);
            //추천 여행지 목록 취득
            List<Destination> recommendList = recommendService.getRecommendList(loginID);
            model.addAttribute("destlist", response);
        }
        model.addAttribute("response", response);
        return "recommend/recommendlist";
    }
}
