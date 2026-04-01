package com.example.yogi.search.controller;

import com.example.yogi.search.dto.DestDetailRequest;
import com.example.yogi.search.dto.DestDetailResponse;
import com.example.yogi.search.dto.DestinationResponse;
import com.example.yogi.search.entity.Destination;
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

    //여행지 상세
    @GetMapping("/searchdest/detail")
    public String index(Model model, DestDetailRequest request, HttpSession session){
        Destination destination = destinationDetailService.getDestDetail(request);

        DestDetailResponse response=new DestDetailResponse(destination);

        String id =(String) session.getAttribute("loginID");
        if(null!=id){
           response.setLiked(destinationDetailService.isLike(id,request.getDestId()));
        }

        model.addAttribute("detail",response);
        return "searchdest/detailview";
    }
}
