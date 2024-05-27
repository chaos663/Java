package com.honey.spring;

import com.honey.spring.repository.MemberRepository;
import com.honey.spring.repository.MemoryMemberRepository;
import com.honey.spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Configuration
public class SpringConfig {

    @Bean // bean 에 등록하기
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
