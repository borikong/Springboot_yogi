package com.example.yogi.search.service;

import com.example.yogi.search.dto.DestDetailRequest;
import com.example.yogi.search.dto.DestDetailResponse;
import com.example.yogi.search.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DestinationDetailServiceImpl implements DestinationDetailService{
    private final DestinationRepository destinationRepository;

    @Override
    public DestDetailResponse getDestDetail(DestDetailRequest request) {
        return destinationRepository.findById(request.getDestId())
                .map(DestDetailResponse::new).orElseThrow(()->new RuntimeException("Destination Not Found"));
    }
}
