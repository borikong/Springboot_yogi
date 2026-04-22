package com.example.yogi.member.controller;

import com.example.yogi.member.dto.MemberRequest;
import com.example.yogi.member.entity.Member;
import com.example.yogi.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class LoginController {
    MemberService memberService;

    @PostMapping("/logintest")
    public String LoginTest(HttpSession session){
        session.setAttribute("loginID","testId");
        return "home";
    }

    //로그인 화면으로
    @GetMapping("/login")
    public String login(HttpSession session){
        if(null!=session.getAttribute("loginId")){
            session.removeAttribute("loginId");
        }
        return "member/login";
    }

    //로그인 처리
    @PostMapping("/login")
    public String loginProc(HttpSession session, Model model, MemberRequest request){

        Member member;
        try {
            member=memberService.getMemberById(request.getId());
            //비밀번호 불일치
            if(!member.getPass().equals(request.getPass())){
                model.addAttribute("check",0);
                return "member/login";
            }
        }catch (Exception e){
            //아이디 없음
            model.addAttribute("check",-1);
            return "member/login";
        }

        session.setAttribute("loginID",request.getId());
        return "home";
    }

    //아이디찾기 화면으로
    @GetMapping("/searchId")
    public String searchId(){
        return "/member/searchid";
    }

    //아이디 찾기
    @PostMapping("/searchId")
    public String searchIdProc(Model model,MemberRequest request){
        Member member=memberService.searchId(request.getName(),request.getEmail());
        if(null!=member){
            model.addAttribute("id",member.getId());
        }
        return "/member/searchidProc";
    }

    //비밀번호찾기 화면으로
    @GetMapping("/searchPass")
    public String searchPass(){
        return "/member/searchpass";
    }

    //비밀번호 찾기
    @PostMapping("/searchPass")
    public String searchPassProc(Model model,MemberRequest request){
        Member member=memberService.searchPass(request.getId(),request.getName(),request.getEmail());
        if(null!=member){
            model.addAttribute("pass",member.getPass());
        }
        return "/member/searchpassProc";
    }

}
