package honey.core;

import honey.core.discount.FixDiscountPolicy;
import honey.core.member.MemberService;
import honey.core.member.MemberServiceImpl;
import honey.core.member.MemoryMemberRepository;
import honey.core.order.OrderService;
import honey.core.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(),new FixDiscountPolicy());
    }
}
