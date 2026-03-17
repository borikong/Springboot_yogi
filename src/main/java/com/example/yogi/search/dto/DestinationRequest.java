package com.example.yogi.search.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinationRequest {

    String loginId;
    String keyword;         //검색 키워드
    String condition;       //검색 조건 1:관광지 2:나라
    String money;
    String landscape;
    String fun;


    public String getKeyword(){
        return (this.keyword==null?"":this.keyword);
    }

}
