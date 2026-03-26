package com.example.yogi.search.service;

import com.example.yogi.search.dto.RecommendResponse;

import java.util.List;

public interface RecommendService {
    //추천 여행지 리스트 취득
    RecommendResponse getRecommendList(String id);
}
