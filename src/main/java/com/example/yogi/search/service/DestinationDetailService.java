package com.example.yogi.search.service;

import com.example.yogi.search.dto.DestDetailRequest;
import com.example.yogi.search.dto.DestDetailResponse;

public interface DestinationDetailService {

    DestDetailResponse getDestDetail(DestDetailRequest request);
}
