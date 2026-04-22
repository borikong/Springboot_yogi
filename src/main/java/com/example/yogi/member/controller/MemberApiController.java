package com.example.yogi.member.controller;

import com.example.yogi.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;
    //좋아요(하트 클릭시 insert delete 처리)
    @PostMapping("/member/like")
    public Map<String, Object> like(@RequestParam String cmd,
                                    @RequestParam String destId,
                                    HttpSession session) {

        String loginID = (String) session.getAttribute("loginID");

        if (loginID == null) {
            return Map.of("success", false, "message", "login required");
        }

        if (cmd.equals("like")) {
            memberService.insertLike(loginID, destId);
        } else {
            memberService.deleteLike(loginID, destId);
        }

        return Map.of("success", true);
    }
}
