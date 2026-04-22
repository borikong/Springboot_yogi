package com.example.yogi.member.service;

import com.example.yogi.member.dto.MemberRequest;
import com.example.yogi.member.entity.Member;

import java.util.List;

public interface MemberService {

    //ID로 사용자 검색
    Member getMemberById(String id);
    //관심여행지 목록 번호 취득
    List<Long> findUserLikeById(String id);
    //아이디 중복 확인
    boolean existsById(String id);
    //이메일 중복 확인
    boolean existsByEmail(String email);
    //신규 멤버 등록
    boolean create(MemberRequest request);
    // name, email로 id 찾기
    Member searchId(String name, String email);
    // id, name, email로 비밀번호 찾기
    Member searchPass(String id, String name, String email);
    // 좋아요 처리
    void insertLike(String loginId,String destId);
    // 좋아요 취소 처리
    void deleteLike(String loginId,String destId);
    boolean isLike(String id, String destId);
}
