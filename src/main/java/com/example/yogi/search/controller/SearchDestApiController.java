package com.example.yogi.search.controller;

import com.example.yogi.search.dto.DestinationRequest;
import com.example.yogi.search.dto.DestinationResponse;
import com.example.yogi.search.service.DestinationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SearchDestApiController {

    private final DestinationService destinationService;

    //검색
    @GetMapping("/searchdest/destinations")
    public List<DestinationResponse> Search(DestinationRequest request){
        return destinationService.searchDestByKeyword(request);
    }

    //좋아요(하트 클릭시 insert delete 처리)
    @PostMapping("/searchdest/like")
    public Map<String, Object> like(@RequestParam String cmd,
                                    @RequestParam String destId,
                                    HttpSession session) {

        String loginID = (String) session.getAttribute("loginID");

        if (loginID == null) {
            return Map.of("success", false, "message", "login required");
        }

        if (cmd.equals("like")) {
            destinationService.insertLike(loginID, destId);
        } else {
            destinationService.deleteLike(loginID, destId);
        }

        return Map.of("success", true);
    }
}
