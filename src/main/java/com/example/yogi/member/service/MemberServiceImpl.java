package com.example.yogi.member.service;

import com.example.yogi.member.dto.MemberRequest;
import com.example.yogi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public List<Long> findUserLikeById(String id){
        List<Long> userLikeList = memberRepository.findById(id)
                .map(member -> Arrays.stream(member.getUserlike().split(",")))
                .orElseGet(Stream::empty)
                .map(String::trim)
                .map(Long::parseLong)
                .toList();
        return userLikeList;
    }
}
