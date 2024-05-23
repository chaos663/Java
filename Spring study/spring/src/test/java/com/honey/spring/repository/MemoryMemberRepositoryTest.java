package com.honey.spring.repository;

// 필요한 클래스와 정적 메서드를 임포트
import com.honey.spring.domain.Member;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    // MemoryMemberRepository의 인스턴스를 생성하여 테스트에 사용
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //@AfterEach annotation은 각 메서드가 끝날 때 마다 다음의 코드 실행. 콜백 메서드
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        // 새로운 멤버 객체를 생성하고 이름을 설정
        Member member = new Member();
        member.setName("Spring");

        // 멤버를 저장소에 저장
        repository.save(member);

        // 멤버의 ID로 저장소에서 멤버를 검색
        Member result = repository.findById(member.getId()).get();

        // 저장된 멤버와 검색된 멤버가 동일한지 확인
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        // 두 개의 새로운 멤버 객체를 생성하고 이름을 설정
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // 이름으로 멤버를 검색
        Member result = repository.findByName("spring1").get();

        // 이름으로 검색된 멤버가 예상된 멤버와 동일한지 확인
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        // 두 개의 새로운 멤버 객체를 생성하고 이름을 설정
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // 저장소에서 모든 멤버를 검색
        List<Member> result = repository.findAll();

        // 검색된 멤버의 수가 예상된 수와 동일한지 확인
        assertThat(result.size()).isEqualTo(2);
    }



}
