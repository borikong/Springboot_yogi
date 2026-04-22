package com.example.yogi.member.controller;

import com.example.yogi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RegistApiController {
    private final MemberService memberService;
    @GetMapping("/check-id")
    public Map<String, Object> checkId(@RequestParam String id) {

        id=id.replace(" ","");
        boolean exists = memberService.existsById(id); // DB 조회

        Map<String, Object> result = new HashMap<>();
        result.put("result", !exists); // true = 사용 가능

        return result;
    }

    @GetMapping("/check-email")
    public Map<String, Object> checkEmail(@RequestParam String email) {

        email=email.replace(" ","");

        boolean exists = memberService.existsByEmail(email); // DB 조회

        Map<String, Object> result = new HashMap<>();
        result.put("result", !exists); // true = 사용 가능

        return result;
    }
}
