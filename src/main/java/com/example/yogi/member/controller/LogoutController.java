package com.example.yogi.member.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class LogoutController {
    @PostMapping("/logout")
    public String LoginTest(HttpSession session){
        session.removeAttribute("loginID");
        return "home";
    }
}
