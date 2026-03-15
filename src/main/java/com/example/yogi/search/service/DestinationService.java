package com.example.yogi.search.service;

import com.example.yogi.search.dto.DestinationRequest;
import com.example.yogi.search.dto.DestinationResponse;
import java.util.List;

public interface DestinationService {

    List<DestinationResponse> searchDestByKeyword(DestinationRequest destinationRequest);

}
