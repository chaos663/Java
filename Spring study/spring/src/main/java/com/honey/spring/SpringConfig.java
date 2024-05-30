package com.honey.spring;

import com.honey.spring.aop.TimeTraceApp;
import com.honey.spring.repository.*;
import com.honey.spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.awt.*;

@Configuration // 스프링 설정 클래스임을 명시
public class SpringConfig {


//    private EntityManager em;
//    private DataSource dataSource; // 데이터 소스 객체를 저장하기 위한 필드

    // SpringConfig 클래스의 생성자. 데이터 소스를 주입받아 초기화
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
    // Spring Jpa Data



    @Bean // 스프링 빈에 등록하기 위한 어노테이션
    public MemberService memberService(){
        // MemberService 객체를 생성하여 스프링 빈에 등록
        return new MemberService(memberRepository);
    }

//    @Bean // 스프링 빈에 등록하기 위한 어노테이션
//    public MemberRepository memberRepository(){
        // MemoryMemberRepository 대신 JdbcMemberRepository를 스프링 빈에 등록
        //return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }

}
