package com.example.yogi.member.service;

import com.example.yogi.member.dto.MemberRequest;
import com.example.yogi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public String findUserLikeById(String id){

        return (null==id?null:memberRepository.findById(id)
                .map(member -> member.getUserlike())
                .orElse(null));
    }
}
