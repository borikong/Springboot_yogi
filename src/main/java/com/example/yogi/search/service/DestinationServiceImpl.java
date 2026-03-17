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

    //우선순위로 검색
    @Override
    public List<DestinationResponse> searchDestByPriority(DestinationRequest request) {
        List<Destination> destList=destinationRepository.findAll();
        List<DestinationResponse> responsesList = new ArrayList<>();
        int attribute_num = 3; // 속성 개수

        //우선순위 가져오기
        int money = Integer.parseInt(request.getMoney());
        int landscape = Integer.parseInt(request.getLandscape());
        int fun = Integer.parseInt(request.getFun());

        // 가중치 계산
        double money_weight = calWeight(money, attribute_num);
        double land_weight = calWeight(landscape, attribute_num);
        double fun_weight = calWeight(fun, attribute_num);

        // 가중치 점수 곱한 벡터 만들어주기
        for (int i = 0; i < destList.size(); i++) {
            // money 속성의 경우 점수가 낮을수록 좋기 때문에 숫자를 역으로 바꿔줌
            double origin_money = 1 - destList.get(i).getDestMoney();
            double origin_land = destList.get(i).getDestLandscape();
            double origin_fun = destList.get(i).getDestFun();
            double total = (origin_money) * money_weight + origin_land * land_weight + origin_fun * fun_weight;

            DestinationResponse dest =new DestinationResponse(destList.get(i));
            dest.setDestTotal(total);
            responsesList.add(dest);
        }

        //내림차순 정렬
        responsesList.sort((a,b)->Double.compare(b.getDestTotal(),a.getDestTotal()));

        return responsesList;
    }


    // 우선순위 가중치 계산(Rank Order Cedtroid)
    private float calWeight(int priority, int attnum) {
        float weight = 0;
        for (int k = priority; k <= attnum; k++) {
            weight += 1 / (float) k;
        }
        return weight * (1 / (float) attnum);
    }

}
