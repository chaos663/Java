package com.honey.spring.domain;

import jakarta.persistence.*;

// Member 클래스 정의
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    // ID를 반환하는 getter 메서드
    public Long getId() {
        return id;
    }

    // ID를 설정하는 setter 메서드
    public void setId(Long id) {
        this.id = id;
    }

    // 이름을 반환하는 getter 메서드
    public String getName() {
        return name;
    }

    // 이름을 설정하는 setter 메서드
    public void setName(String name) {
        this.name = name;
    }
}
