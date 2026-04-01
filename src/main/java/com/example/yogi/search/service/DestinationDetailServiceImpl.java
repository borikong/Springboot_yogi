package com.example.yogi.search.service;

import com.example.yogi.member.repository.MemberRepository;
import com.example.yogi.search.dto.DestDetailRequest;
import com.example.yogi.search.dto.DestDetailResponse;
import com.example.yogi.search.entity.Destination;
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
    public Destination getDestDetail(DestDetailRequest request) {
        Destination destination=destinationRepository.findById(request.getDestId()).orElseThrow();

        return destination;
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
