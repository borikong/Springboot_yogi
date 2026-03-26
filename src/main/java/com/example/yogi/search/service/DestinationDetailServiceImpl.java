package com.example.yogi.search.service;

import com.example.yogi.member.repository.MemberRepository;
import com.example.yogi.search.dto.DestDetailRequest;
import com.example.yogi.search.dto.DestDetailResponse;
import com.example.yogi.search.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DestinationDetailServiceImpl implements DestinationDetailService{
    private final DestinationRepository destinationRepository;
    private final MemberRepository memberRepository;

    @Override
    public DestDetailResponse getDestDetail(String id, DestDetailRequest request) {
        DestDetailResponse response=destinationRepository.findById(request.getDestId())
                .map(DestDetailResponse::new).orElseThrow(()->new RuntimeException("Destination Not Found"));
        response.setLiked(isLike(id,request.getDestId()));
        return response;
    }
    //여행지가 좋아요 표시한 여행지인지 여부
    private boolean isLike(String id, String destId){
        List<Long> userLikeList = memberRepository.findById(id)
                .map(member -> Arrays.stream(member.getUserlike().split(",")))
                .orElseGet(Stream::empty)
                .map(String::trim)
                .map(Long::parseLong)
                .toList();

        return userLikeList.contains(Long.parseLong(destId));
    }
}
