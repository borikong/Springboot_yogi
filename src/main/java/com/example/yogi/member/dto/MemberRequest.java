package com.example.yogi.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequest {
    String id;
    String pass;
    String name;
    String phone;
    String email;
    String zipcode;
    String address1;
    String address2;
    String userlike;

    String idCheckFlg;
    String emailCheckFlg;
}
