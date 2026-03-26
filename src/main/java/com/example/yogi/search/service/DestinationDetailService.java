package com.example.yogi.search.service;

import com.example.yogi.search.dto.DestDetailRequest;
import com.example.yogi.search.dto.DestDetailResponse;

import java.util.List;

public interface DestinationDetailService {

    //여행지 상세정보 취득
    DestDetailResponse getDestDetail(String id,DestDetailRequest request);
}
