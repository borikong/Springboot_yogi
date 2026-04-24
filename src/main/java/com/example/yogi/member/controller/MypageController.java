package com.example.yogi.member.controller;

import com.example.yogi.member.dto.MemberRequest;
import com.example.yogi.member.service.MemberService;
import com.example.yogi.search.dto.DestinationResponse;
import com.example.yogi.search.entity.Destination;
import com.example.yogi.search.service.DestinationService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class MypageController {
    private final MemberService memberService;
    private final DestinationService destinationService;

    //마이페이지 화면으로
    @GetMapping({"/member/mypage"})
    public String index(Model model, HttpSession session, MemberRequest request){

        //관심 여행지 목록 취득
        setLikeList(model,(String)session.getAttribute("loginID"));

        return "member/mypage";
    }

    //정보수정 화면으로
    @GetMapping({"/member/modify"})
    public String modify(Model model,MemberRequest request){

        return "member/modify";
    }

    //회원탈퇴 화면으로
    @GetMapping({"/member/delete"})
    public String delete(Model model){

        return "member/delete";
    }

    //찜삭제
    @GetMapping({"/member/deletelike"})
    public String deleteLike(Model model,HttpSession session,@RequestParam String destId){
        memberService.deleteLike(session.getAttribute("loginID").toString(),destId);
        //관심 여행지 목록 취득
        setLikeList(model,(String)session.getAttribute("loginID"));
        return "member/mypage";
    }

    //관심여행지 데이터 취득
    private void setLikeList(Model model, String loginId) {
        if (loginId != null) {
            List<DestinationResponse> likeList=new ArrayList<>();
            List<Destination> destList=destinationService.searchDestListByNo(memberService.findUserLikeById(loginId));
            for(Destination dest:destList){
                likeList.add(new DestinationResponse(dest));
            }
            model.addAttribute("likeList",likeList);
        }
    }

}
