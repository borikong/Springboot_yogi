package com.example.yogi.search.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*
 * 여행지 검색 response 클래스
 */
@Getter
@Setter
public class DestinationResponse {
    private List<DestItemResponse> destList;
    private List<Long> likeList;
}
