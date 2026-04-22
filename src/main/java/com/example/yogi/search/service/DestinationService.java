package com.example.yogi.search.service;

import com.example.yogi.search.dto.DestDetailRequest;
import com.example.yogi.search.dto.DestinationRequest;
import com.example.yogi.search.entity.Destination;

import java.util.List;

public interface DestinationService {

    //키워드로 여행지 검색
    List<Destination> searchDestByKeyword(DestinationRequest request);
    //우선순위로 여행지 검색
    List<Destination> searchDestByPriority(DestinationRequest request);

    //여행지 목록번호 리스트로 여행지 데이터 취득
    List<Destination> searchDestListByNo(List<Long> idlist);

    //여행지 상세정보 취득
    Destination getDestDetail(DestDetailRequest request);
}
