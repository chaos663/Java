package com.honey.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // "hello" 엔드포인트에 대한 GET 요청을 처리하는 메서드
    @GetMapping("hello")
    public String hello(Model model){
        // 모델에 "data" 키로 "helloJava" 값을 추가
        model.addAttribute("data", "helloJava"); // data는 키, helloJava는 값
        // "hello" 뷰를 반환 (resources - templates - hello 템플릿을 찾아서 렌더링)
        return "hello";
    }

    // "hello-mvc" 엔드포인트에 대한 GET 요청을 처리하는 메서드
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model){
        // 모델에 "name" 키로 파라미터로 받은 name 값을 추가
        model.addAttribute("name", name);
        // "hello-template" 뷰를 반환
        return "hello-template";
    }

    // "hello-string" 엔드포인트에 대한 GET 요청을 처리하는 메서드
    @GetMapping("hello-string")
    @ResponseBody // 뷰 대신 문자열을 HTTP 응답 본문으로 직접 반환
    public String helloString(@RequestParam(value = "name") String name){
        // "hello"와 파라미터로 받은 name 값을 결합하여 반환
        return "hello " + name; // 응답 결과 : "hello name"
    }

    // "hello-api" 엔드포인트에 대한 GET 요청을 처리하는 메서드
    @GetMapping("hello-api")
    @ResponseBody // 객체를 JSON 형식으로 HTTP 응답 본문으로 직접 반환
    public Hello helloApi(@RequestParam("name") String name) {
        // Hello 객체 생성 후 name 값을 설정
        Hello hello = new Hello();
        hello.setName(name);
        // Hello 객체를 반환 (JSON으로 변환되어 전송됨)
        return hello;
    }

    // 내부 클래스 Hello 정의
    static class Hello{
        private String name;

        // name 필드의 getter 메서드
        public String getName() {
            return name;
        }

        // name 필드의 setter 메서드
        public void setName(String name) {
            this.name = name;
        }
    }
}
