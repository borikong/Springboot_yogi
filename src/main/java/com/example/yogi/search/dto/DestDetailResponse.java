package com.example.yogi.search.dto;

import com.example.yogi.search.entity.Destination;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DestDetailResponse {
    private Long destId;                    //여행지 id(식별번호 unique)
    private String destName;                //여행지 이름
    private String destCountry;             //여행지 국가
    private String destNameEng;             //여행지 이름 영어
    private String destContent;             //여행지 소개글
    private String destReference;           //여행지 소개 참조
    private Double destMoney;               //여행지 비용 속성 점수
    private Double destLandscape;           //여행지 경치 속성 점수
    private Double destFun;                 //여행지 재미 속성 점수
    private String destTag;                 //여행지 태그, 공백으로 분리
    private String destImg;                 //여행지 이미지 주소
    private Integer destCnt;                //여행지 좋아요 개수
    private LocalDateTime insDate;          //데이터 삽입 일시
    private LocalDateTime updDate;          //데이터 업데이트 일시
    private boolean isLiked;                //좋아요 표시 플래그
    private String destSummary;             //DEST_CONTENT를 190자까지 잘라냄

    //Entity -> DTO
    public DestDetailResponse(Destination destination){
        this.destId=destination.getDestId();
        this.destName=destination.getDestName();
        this.destCountry=destination.getDestCountry();
        this.destNameEng=destination.getDestNameEng();
        this.destContent=destination.getDestContent();
        this.destReference=destination.getDestReference();
        this.destMoney=destination.getDestMoney();
        this.destLandscape=destination.getDestLandscape();
        this.destFun=destination.getDestFun();
        this.destTag=destination.getDestTag();
        this.destImg=destination.getDestImg();
        this.destCnt=destination.getDestCnt();
        this.insDate=destination.getInsDate();
        this.updDate=destination.getUpdDate();
        setDestSummary();
    }

    private void setDestSummary() {
        if(this.destContent.length()>190){
            this.destSummary=this.destContent.substring(0,190);
        }else this.destSummary=this.destContent;
    }
}
