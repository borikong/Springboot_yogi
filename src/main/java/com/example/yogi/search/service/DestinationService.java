package com.example.yogi.search.service;

import com.example.yogi.search.dto.DestinationRequest;
import com.example.yogi.search.dto.DestinationResponse;
import java.util.List;

public interface DestinationService {

    List<DestinationResponse> searchDestByKeyword(DestinationRequest request);
    List<DestinationResponse> searchDestByPriority(DestinationRequest request);
    void insertLike(String loginId,String destId);
    void deleteLike(String loginId,String destId);
    public List<Long> findUserLikeById(String id);

}
