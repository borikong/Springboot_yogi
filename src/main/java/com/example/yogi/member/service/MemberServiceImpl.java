package com.example.yogi.member.service;

import com.example.yogi.board.entity.Board;
import com.example.yogi.member.dto.MemberRequest;
import com.example.yogi.member.entity.Member;
import com.example.yogi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    //ID로 사용자 검색
    @Override
    public Member getMemberById(String id) {
        return memberRepository.findById(id).orElseThrow();
    }

    //관심여행지 목록 번호 취득
    @Override
    public List<Long> findUserLikeById(String id){
        List<Long> userLikeList=null;
        try{
            userLikeList = memberRepository.findById(id)
                    .map(member -> member.getUserlike())
                    .filter(Objects::nonNull)
                    .map(str -> Arrays.stream(str.split(",")))
                    .orElseGet(Stream::empty)
                    .map(String::trim)
                    .map(Long::parseLong)
                    .toList();
        }catch (Exception e){
            return new ArrayList<>();
        }

        return userLikeList;
    }

    //아이디 중복 확인
    @Override
    public boolean existsById(String id){
        return memberRepository.existsById(id);
    }

    //이메일 중복 확인
    @Override
    public boolean existsByEmail(String email){
        return memberRepository.existsByEmail(email);
    }

    //신규 멤버 등록
    @Override
    public boolean create(MemberRequest request) {
        Member member= Member.create(request);
        try {
            memberRepository.save(member);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    // name, email로 id 찾기
    @Override
    public Member searchId(String name, String email) {
        return memberRepository.findByNameAndEmail(name,email);
    }

    // id, name, email로 비밀번호 찾기
    @Override
    public Member searchPass(String id,String name, String email) {
        return memberRepository.findByIdAndNameAndEmail(id,name,email);
    }

    // 좋아요 처리
    @Override
    public void insertLike(String loginId,String destId) {
        List<Long> userlikeList=new ArrayList<>();
        try{
            userlikeList = new ArrayList<>(this.findUserLikeById(loginId));
        }catch (Exception e){

        }

        userlikeList.add(Long.parseLong(destId));

        List<Long> uniqueList = new ArrayList<>(new HashSet<>(userlikeList));
        String userlike = "";
        for (long id : uniqueList){
            userlike+=id+",";
        }

        Member mem = memberRepository.findById(loginId).orElseThrow();

        mem.setUserlike(userlike);
        memberRepository.save(mem);
    }

    // 좋아요 취소 처리
    @Override
    public void deleteLike(String loginId,String destId) {
        List<Long> userlikeList = new ArrayList<>(this.findUserLikeById(loginId));

        userlikeList.remove(Long.valueOf(destId));

        List<Long> uniqueList = new ArrayList<>(new HashSet<>(userlikeList));
        String userlike = "";
        for (long id : uniqueList){
            userlike+=id+",";
        }

        Member mem = memberRepository.findById(loginId).orElseThrow();
        mem.setUserlike(userlike);
        memberRepository.save(mem);
    }

    //여행지가 좋아요 표시한 여행지인지 여부
    @Override
    public boolean isLike(String id, String destId){
        List<Long> userLikeList = memberRepository.findById(id)
                .map(member -> Arrays.stream(member.getUserlike().split(",")))
                .orElseGet(Stream::empty)
                .map(String::trim)
                .map(Long::parseLong)
                .toList();

        return userLikeList.contains(Long.parseLong(destId));
    }
}
