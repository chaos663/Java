package com.honey.spring.service;

import com.honey.spring.domain.Member;
import com.honey.spring.repository.MemberRepository;
import com.honey.spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //테스트는 한글 메서드명으로 적어서 사용해도 됨
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given, when, then 문법
        // given
            Member member = new Member();
            member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }
    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //        memberService.join(member2);
        //        try {
        //            memberService.join(member2);
        //            fail();
        //        }catch (IllegalStateException e){
        //            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //        }
        
        // try, catch 문 쓰기도 그렇다? assertThrows를 사용해보자

//        assertThrows(IllegalStateException.class,()-> memberService.join(member2)); // return success

        // 메시지 검증

        IllegalStateException e = assertThrows(IllegalStateException.class,()-> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        assertThrows(NullPointerException.class,()-> memberService.join(member2)); // return error
        // 예외가 잘 터지면 테스트 성공


        //then



    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}