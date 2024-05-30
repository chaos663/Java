package com.honey.spring.service;

import com.honey.spring.domain.Member;
import com.honey.spring.repository.MemberRepository;
import com.honey.spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    // MemberRepository 인터페이스를 구현한 MemoryMemberRepository 인스턴스 생성
    private final MemberRepository memberRepository;

    // 외부로부터 인스턴스를 넣어주는 것을 DI(Dependency Injection - 의존성 주입)이라고 한다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입 메서드
    public Long join(Member member) {

        long start = System.currentTimeMillis();
        try {
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member); // 회원 정보 저장
            return member.getId(); // 회원 ID 반환
        }finally {
            long finish = System.currentTimeMillis();
            long time = finish - start;
            System.out.println("join = " + time + "ms");
        }

    }

    // 중복 회원 검증 메서드
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // 이름으로 회원 조회
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다."); // 중복 회원 예외 처리
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
