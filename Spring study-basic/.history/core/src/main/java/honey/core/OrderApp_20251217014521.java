package honey.core;

import honey.core.member.Grade;
import honey.core.member.Member;
import honey.core.member.MemberService;
import honey.core.order.Order;
import honey.core.order.OrderService;

public class OrderApp {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA",20000);

        System.out.println("order = " + order);
//        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
