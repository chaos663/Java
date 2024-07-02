package honey.core;

import honey.core.discount.DiscountPolicy;
import honey.core.discount.FixDiscountPolicy;
import honey.core.discount.RateDiscountPolicy;
import honey.core.member.MemberRepository;
import honey.core.member.MemberService;
import honey.core.member.MemberServiceImpl;
import honey.core.member.MemoryMemberRepository;
import honey.core.order.OrderService;
import honey.core.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService(){

        return new MemberServiceImpl(memberRepository());
    }
    // memberRepository는 MemoryMemberRepository 사용
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }
    public DiscountPolicy discountPolicy(){
        // 정액 할인
//        return new FixDiscountPolicy();
        //정률 할인, 금액의 10%
        return new RateDiscountPolicy();
    }
}
