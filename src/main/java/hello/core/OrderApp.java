package hello.core;

import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) { // 아래 처럼 메인 메소드로 하는 것은 좋지 않다. jUnit으로 테스트 코드를 작성하자.
        /* 의존관계 주입 이전
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        */

        // 의존관계 주입 이후
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        // 회원 가입
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        // 주문
        Order order = orderService.createOrder(memberId, "item1", 10000);

        System.out.println("order = " + order);
        //System.out.println("order.calculatePrice() = " + order.calculatePrice());
    }
}
