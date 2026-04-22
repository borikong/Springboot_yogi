package com.example.yogi.inquiry.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class InquiryController {

    @RequestMapping({"/inquiry"})
    public String index(){
        return "inquiry/Inquiry_Board_List";
    }
}
