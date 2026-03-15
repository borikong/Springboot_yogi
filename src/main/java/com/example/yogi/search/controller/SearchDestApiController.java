package com.example.yogi.search.controller;

import com.example.yogi.search.dto.DestinationRequest;
import com.example.yogi.search.dto.DestinationResponse;
import com.example.yogi.search.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchDestApiController {

    private final DestinationService destinationService;

    @GetMapping("/searchdest/destinations")
    public List<DestinationResponse> Search(DestinationRequest request){
        return destinationService.searchDestByKeyword(request);
    }
}
