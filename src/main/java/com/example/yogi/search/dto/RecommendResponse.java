package com.example.yogi.search.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
/*
 * 추천 여행지 response 클래스
 */
@Getter
@Setter
public class RecommendResponse {

    public enum Mode {
        HAS_RECOMMEND,   // 추천 리스트 있음
        NOT_LOGIN,       // 로그인 안됨
        EMPTY_FAVORITE   // 찜 없음
    }

    private List<DestItemResponse> destList;
    private Mode mode;
    private List<Long> likeList;
}
