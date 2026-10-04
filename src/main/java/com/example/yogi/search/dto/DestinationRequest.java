package com.example.yogi.search.dto;

import lombok.Getter;
import lombok.Setter;
/*
 * 여행지 검색 request 클래스
 */
@Getter
@Setter
public class DestinationRequest {

    private String loginId;
    private String keyword;         //검색 키워드
    private String condition;       //검색 조건 1:관광지 2:나라
    private String money;
    private String landscape;
    private String fun;

    public String getKeyword(){
        return (this.keyword==null?"":this.keyword);
    }

}
