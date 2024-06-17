package honey.core;

import honey.core.member.Grade;
import honey.core.member.Member;
import honey.core.member.MemberService;
import honey.core.member.MemberServiceImpl;
import honey.core.order.Order;
import honey.core.order.OrderService;
import honey.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA",10000);

        System.out.println("order = " + order);
//        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
