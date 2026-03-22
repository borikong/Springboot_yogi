package com.example.yogi.member.service;

import com.example.yogi.member.dto.MemberRequest;

import java.util.List;

public interface MemberService {
    List<Long> findUserLikeById(String id);
}
