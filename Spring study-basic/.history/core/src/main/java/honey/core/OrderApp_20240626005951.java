package honey.core;

import honey.core.member.Grade;
import honey.core.member.Member;
import honey.core.member.MemberService;
import honey.core.order.Order;
import honey.core.order.OrderService;

public class OrderApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        OrderService orderService = appConfig.orderService();
        MemberService memberService = appConfig.memberService();

//        MemberService memberService = new MemberServiceImpl(null);
//        OrderService orderService = new OrderServiceImpl(null,null);

        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA",20000);

        System.out.println("order = " + order);
//        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
