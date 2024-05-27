package com.honey.spring.repository;

import com.honey.spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
public class MemoryMemberRepository implements MemberRepository{

    // 멤버를 저장할 저장소와 멤버 ID를 위한 시퀀스 변수
    private static Map<Long, Member > store = new HashMap<>();
    private static long sequence = 0L;

    // 멤버를 저장하는 메서드
    @Override
    public Member save(Member member) {
        // 멤버의 ID를 설정하고 저장소에 멤버를 저장
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // ID로 멤버를 찾는 메서드
    @Override
    public Optional<Member> findById(Long id) {
        // ID로 저장소에서 멤버를 찾아 반환
        return Optional.ofNullable(store.get(id));
    }

    // 이름으로 멤버를 찾는 메서드
    @Override
    public Optional<Member> findByName(String name) {
        // 저장소의 모든 멤버 중 이름이 일치하는 멤버를 찾아 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    // 모든 멤버를 반환하는 메서드
    @Override
    public List<Member> findAll() {
        // 저장소의 모든 멤버를 리스트로 반환
        return new ArrayList<>(store.values());
    }

    // store를 전부 비워주는 메서드
    public void clearStore(){
        store.clear();
    }
}
