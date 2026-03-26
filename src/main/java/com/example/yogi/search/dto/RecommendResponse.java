package com.example.yogi.search.dto;

import com.example.yogi.search.entity.Destination;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RecommendResponse {

    public enum Mode {
        HAS_RECOMMEND,   // 추천 리스트 있음
        NOT_LOGIN,       // 로그인 안됨
        EMPTY_FAVORITE   // 찜 없음
    }

    private List<DestinationResponse> destList;
    private Mode mode;
}
