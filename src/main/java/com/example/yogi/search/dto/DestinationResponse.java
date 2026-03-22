package com.example.yogi.search.dto;

import com.example.yogi.search.entity.Destination;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DestinationResponse {

    private Long destId;                    //여행지 id(식별번호 unique)
    private String destName;                //여행지 이름
    private String destCountry;             //여행지 국가
    private String destNameEng;             //여행지 이름 영어
    private String destContent;             //여행지 소개글
    private String destReference;          //여행지 소개 참조
    private Double destMoney;               //여행지 비용 속성 점수
    private Double destLandscape;           //여행지 경치 속성 점수
    private Double destFun;                 //여행지 재미 속성 점수
    private String destTag;                 //여행지 태그, 공백으로 분리
    private String destImg;                 //여행지 이미지 주소
    private Integer destCnt;                //여행지 좋아요 개수
    private LocalDateTime insDate;
    private LocalDateTime updDate;

//    private List<String> likelist;
    private double destTotal;               //가중치를 곱한 여행지 점수
    private String destSummary;             //DEST_CONTENT를 190자까지 잘라냄
    private String money;                   //선택한 우선순위
    private String landscape;               //선택한 우선순위
    private String fun;                     //선택한 우선순위


    //Entity -> DTO
    public DestinationResponse(Destination destination){
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

    public String getDestTagStr() {
        String[] taglist=this.destTag.split(",");
        String str="";
        for(String tag : taglist){
            str+="#"+tag.replace(" ","")+" ";
        }
        return str;
    }
}
