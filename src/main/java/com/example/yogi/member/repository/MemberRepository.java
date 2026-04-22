package com.example.yogi.member.repository;

import com.example.yogi.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,String> {

    boolean existsByEmail(String email);
    Member findByNameAndEmail(String name, String email);
    Member findByIdAndNameAndEmail(String id,String name, String email);
}
