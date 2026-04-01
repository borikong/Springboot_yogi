package com.example.yogi.search.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "destination")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEST_ID")
    private Long destId;                    //여행지 id(식별번호 unique)

    @Column(name = "DEST_NAME")
    private String destName;                //여행지 이름

    @Column(name = "DEST_COUNTRY")
    private String destCountry;             //여행지 국가

    @Column(name = "DEST_NAME_ENG")
    private String destNameEng;             //여행지 이름 영어

    @Column(name = "DEST_CONTENT",columnDefinition = "TEXT")
    private String destContent;             //여행지 소개글

    @Column(name = "DEST_REFERENCE")
    private String destReference;          //여행지 소개 참조

    @Column(name = "DEST_MONEY")
    private Double destMoney;               //여행지 비용 속성 점수

    @Column(name = "DEST_LANDSCAPE")
    private Double destLandscape;           //여행지 경치 속성 점수

    @Column(name = "DEST_FUN")
    private Double destFun;                 //여행지 재미 속성 점수

    @Column(name = "DEST_TAG")
    private String destTag;                 //여행지 태그, 공백으로 분리

    @Column(name = "DEST_IMG")
    private String destImg;                 //여행지 이미지 주소

    @Column(name = "DEST_CNT")
    private Integer destCnt;                //여행지 좋아요 개수

    @Column(name = "INS_DATE")
    private LocalDateTime insDate;

    @Column(name = "UPD_DATE")
    private LocalDateTime updDate;

    @Setter
    @Transient
    private double destTotal;               //가중치를 곱한 여행지 점수
}
