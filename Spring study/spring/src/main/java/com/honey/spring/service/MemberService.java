package com.honey.spring.service;

import com.honey.spring.domain.Member;
import com.honey.spring.repository.MemberRepository;
import com.honey.spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    // MemberRepository 인터페이스를 구현한 MemoryMemberRepository 인스턴스 생성
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원 가입 메서드
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member); // 회원 정보 저장
        return member.getId(); // 회원 ID 반환
    }

    // 중복 회원 검증 메서드
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // 이름으로 회원 조회
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 있는 회원입니다."); // 중복 회원 예외 처리
                });
    }

    /**
     * 
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll(); // 모든 회원 리스트 반환
    }

    // 특정 회원 조회 메서드
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId); // 회원 ID로 조회한 회원 반환
    }
}
