package com.example.yogi.search.service;

import com.example.yogi.search.dto.DestinationRequest;
import com.example.yogi.search.dto.DestinationResponse;
import com.example.yogi.search.entity.Destination;
import com.example.yogi.search.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinationServiceImpl implements DestinationService {
    private final DestinationRepository destinationRepository;

    //키워드로 검색 :: 관광지명/나라
    //TODO condition 넘겨주기, 우선순위 넘겨주기 , like 처리는 api로
    @Override
    public List<DestinationResponse> searchDestByKeyword(DestinationRequest request) {

        List<Destination> destList;

        //관광지명으로 검색
        if("DEST_NAME".equals(request.getCondition())){
            destList=destinationRepository.findByDestNameContaining(request.getKeyword());
        //나라로 검색
        }else if("COUNTRY".equals(request.getCondition())){
            destList=destinationRepository.findByDestCountryContaining(request.getKeyword());
        }else {
            destList=destinationRepository.findAll();
        }

        return destList.stream().map(DestinationResponse::new).toList();
    }

}
