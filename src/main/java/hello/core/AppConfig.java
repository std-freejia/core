package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * 애플리케이션의 실제 동작에 필요한 "구현 객체를 생성" 한다.
 * MemberServiceImpl(), MemoryMemberRepository(),
 * OrderServiceImpl(), FixDiscountPolicy()
 *
 * 생성한 객체 인스턴스의 참조를 "생성자를 통해 주입(연결)" 해준다.
 * MemberServiceImpl -> MemoryMemberRepository
 * OrderServiceImpl -> MemoryMemberRepository, FixDiscountPolicy
 */
public class AppConfig { // 공연 기획자의 역할을 한다.

    public MemberService memberService(){ // 객체의 생성과 연결을 모두 담당한다.
        // 생성자를 통해 구현체를 선택할 수 있도록 한다!
        // DIP의 완성 : MemberServiceImpl은 MemberRepository인 추상에만 의존하면 된다. 구체 클래스를 몰라도 된다!

        return new MemberServiceImpl(new MemoryMemberRepository()); // 의존 관계 주입 (Dependency Injection)
    }

    public OrderService orderService(){
        //MemberRepository 와 DiscountPolicy가 필요하다.
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
