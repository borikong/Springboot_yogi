package com.example.yogi.search.service;

import com.example.yogi.search.dto.DestDetailRequest;
import com.example.yogi.search.entity.Destination;

import java.util.List;

public interface DestinationDetailService {

    //여행지 상세정보 취득
    Destination getDestDetail(DestDetailRequest request);
    boolean isLike(String id, String destId);
}
