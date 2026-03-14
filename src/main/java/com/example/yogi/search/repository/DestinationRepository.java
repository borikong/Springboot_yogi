package com.example.yogi.search.repository;

import com.example.yogi.search.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination,String> {

    // 여행지명으로 여행지 검색
    List<Destination> findByDestNameContaining(String destName);

    // 나라로 여행지 검색
    List<Destination> findByDestCountryContaining(String destContry);

    Destination getDestinationByDestName(String destName);
}
