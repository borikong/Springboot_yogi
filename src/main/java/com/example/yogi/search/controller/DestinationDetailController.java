package com.example.yogi.search.controller;

import com.example.yogi.search.dto.DestDetailRequest;
import com.example.yogi.search.service.DestinationDetailService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DestinationDetailController {
    private final DestinationDetailService destinationDetailService;

    @GetMapping("/searchdest/detail")
    public String index(Model model, DestDetailRequest request, HttpSession session){

        model.addAttribute("detail",destinationDetailService.getDestDetail(session.getAttribute("loginID").toString(),request));
        return "searchdest/detailview";
    }
}
