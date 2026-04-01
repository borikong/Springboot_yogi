package com.example.yogi.search.service;

import com.example.yogi.search.dto.DestinationRequest;
import com.example.yogi.search.entity.Destination;

import java.util.List;

public interface DestinationService {

    //키워드로 여행지 검색
    List<Destination> searchDestByKeyword(DestinationRequest request);
    //우선순위로 여행지 검색
    List<Destination> searchDestByPriority(DestinationRequest request);
    // 좋아요 처리
    void insertLike(String loginId,String destId);
    // 좋아요 취소 처리
    void deleteLike(String loginId,String destId);

}
