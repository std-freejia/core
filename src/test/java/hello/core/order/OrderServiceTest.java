package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest { // 단축키 command + E : 이전 작업 파일

    /* 의존관계 주입 이전
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    */

    //
    MemberService memberService;
    OrderService orderService;

    @BeforeEach // 각 테스트 실행 전에 무조건 실행 되는 메소드.
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        Long memberId = 1L; // Long 객체, long 기본형(primitive type)
        // VIP고객 1명 생성하고 가입처리
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 10000원 주문
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        // VIP가 주문했을 때, 1000원 할인해주는지 확인
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
