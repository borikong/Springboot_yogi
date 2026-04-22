package com.example.yogi.search.controller;

import com.example.yogi.search.dto.DestinationRequest;
import com.example.yogi.search.entity.Destination;
import com.example.yogi.search.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DestinationApiController {

    private final DestinationService destinationService;

    //여행지 검색
    @GetMapping("/searchdest/destinations")
    public List<Destination> Search(DestinationRequest request){
        return destinationService.searchDestByKeyword(request);
    }
}
