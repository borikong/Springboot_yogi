package com.example.yogi.search.controller;

import com.example.yogi.search.dto.DestDetailRequest;
import com.example.yogi.search.service.DestinationDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DestinationDetailController {
    private final DestinationDetailService destinationDetailService;

    @GetMapping("/searchdest/detail")
    public String index(Model model, DestDetailRequest request){

        model.addAttribute("detail",destinationDetailService.getDestDetail(request));
        return "searchdest/detailview";
    }
}
