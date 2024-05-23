package com.honey.spring.repository;

import com.honey.spring.domain.Member;

import java.util.List;
import java.util.Optional;

// MemberRepository 인터페이스 정의
public interface MemberRepository {

    // 멤버를 저장하는 메서드
    Member save(Member member);

    // ID로 멤버를 찾는 메서드
    Optional<Member> findById(Long id);

    // 이름으로 멤버를 찾는 메서드
    Optional<Member> findByName(String name);

    // 모든 멤버를 반환하는 메서드
    List<Member> findAll();
}
