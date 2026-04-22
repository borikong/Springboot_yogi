package com.example.yogi.member.controller;

import com.example.yogi.member.dto.MemberRequest;
import com.example.yogi.member.dto.MemberResponse;
import com.example.yogi.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class RegistController {

    private final MemberService memberService;

    @GetMapping({"/newMember"})
    public String index(Model model, HttpSession session,MemberRequest request){

        if(session.getAttribute("loginID")!=null){
            session.removeAttribute("loginID");
        }

        model.addAttribute("mem",request);
        return "member/regForm";
    }

    @PostMapping({"/regMember"})
    public String register(Model model,MemberRequest request){

        boolean flag = memberService.create(request);
        model.addAttribute("mem",request);
        model.addAttribute("flag",flag);

        return "member/regProc";
    }
}
