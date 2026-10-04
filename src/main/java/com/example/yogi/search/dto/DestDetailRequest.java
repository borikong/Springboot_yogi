package com.example.yogi.search.dto;

import lombok.Getter;
import lombok.Setter;
/*
 * 여행지 상세 페이지 request 클래스
 */
@Getter
@Setter
public class DestDetailRequest {
    private String loginId;
    private String destId;
}
