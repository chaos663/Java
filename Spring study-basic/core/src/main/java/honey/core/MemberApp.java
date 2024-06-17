package honey.core;

import honey.core.member.Grade;
import honey.core.member.Member;
import honey.core.member.MemberService;
import honey.core.member.MemberServiceImpl;

public class MemberApp {

    // psvm(public static void main)을 입력하면 main 함수가 자동적으로 생성
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        // 단축키 Ctrl + Alt + V
        Member member = new Member(1L, "memberA", Grade.VIP);

        // 회원가입
        memberService.join(member);
        // Member 조회
        Member findMember = memberService.findMember(1L);

        // system.out.println 단축키 sout , soutv
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
