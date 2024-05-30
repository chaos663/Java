package com.honey.spring.repository;

import com.honey.spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

// JpaMemberRepository는 JPA를 사용하여 MemberRepository를 구현하는 클래스입니다.
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; // EntityManager를 사용하여 데이터베이스 작업을 수행

    // 생성자에서 EntityManager를 받아와 초기화
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    // Member를 데이터베이스에 저장하는 메서드
    @Override
    public Member save(Member member) {
        em.persist(member); // JPA의 persist 메서드를 사용하여 member를 저장
        return member; // 저장된 member 객체를 반환
    }

    // ID를 통해 Member를 찾는 메서드
    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // JPA의 find 메서드를 사용하여 ID로 member를 검색
        return Optional.ofNullable(member); // 결과를 Optional로 반환, null일 경우 Optional.empty() 반환
    }

    // 이름을 통해 Member를 찾는 메서드
    @Override
    public Optional<Member> findByName(String name) {
        // JPQL을 사용하여 name으로 member를 검색
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name) // name 파라미터 설정
                .getResultList(); // 결과 리스트 반환
        return result.stream().findAny(); // 결과를 Optional로 반환
    }

    // 모든 Member를 찾는 메서드
    @Override
    public List<Member> findAll() {
        // JPQL을 사용하여 모든 member를 검색
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); // 결과 리스트 반환
    }
}
