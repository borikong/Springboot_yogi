package com.example.yogi.member.entity;

import com.example.yogi.member.dto.MemberRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {

    @Id
    private String id;
    private String pass;
    private String name;
    private String phone;
    private String email;
    private String zipcode;
    private String address1;
    private String address2;
    @Setter
    private String userlike;

    public static Member create(MemberRequest request) {
        Member member = new Member();
        member.id=request.getId();
        member.pass=request.getPass();
        member.name=request.getName();
        member.phone=request.getPhone();
        member.email=request.getEmail();
        member.zipcode=request.getZipcode();
        member.address1=request.getAddress1();
        member.address2=request.getAddress2();
        member.userlike="";
        return member;
    }

}
