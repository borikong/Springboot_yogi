package com.example.yogi.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponse {
    String id;
    String userLike;
    String email;

    public MemberResponse(){
    }

    //request to response
    public MemberResponse(MemberRequest request){
        this.setId(request.getId());
        this.setEmail(request.getEmail());
    }
}
