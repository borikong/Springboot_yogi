package com.example.yogi.search.service;

import com.example.yogi.search.dto.RecommendResponse;
import com.example.yogi.search.entity.Destination;

import java.util.List;

public interface RecommendService {
    //추천 여행지 리스트 취득
    List<Destination> getRecommendList(String id);
}
