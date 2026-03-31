package com.example.yogi.search.controller;

import com.example.yogi.search.dto.DestDetailRequest;
import com.example.yogi.search.dto.DestDetailResponse;
import com.example.yogi.search.dto.DestinationResponse;
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
        DestDetailResponse response = destinationDetailService.getDestDetail(request);

        String id =(String) session.getAttribute("loginID");
        if(null!=id){
           response.setLiked(destinationDetailService.isLike(id,request.getDestId()));
        }

        model.addAttribute("detail",response);
        return "searchdest/detailview";
    }
}
