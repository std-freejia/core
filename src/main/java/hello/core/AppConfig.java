package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 애플리케이션의 실제 동작에 필요한 "구현 객체를 생성" 한다.
 * MemberServiceImpl(), MemoryMemberRepository(),
 * OrderServiceImpl(), FixDiscountPolicy()
 *
 * 생성한 객체 인스턴스의 참조를 "생성자를 통해 주입(연결)" 해준다.
 * MemberServiceImpl -> MemoryMemberRepository
 * OrderServiceImpl -> MemoryMemberRepository, FixDiscountPolicy
 *
 * 생성자를 통해 구현체를 선택할 수 있도록 한다!
 * DIP의 완성 : MemberServiceImpl은 MemberRepository인 추상에만 의존하면 된다. 구체 클래스를 몰라도 된다!
 */

@Configuration // 스프링으로 전환하기. 스프링 컨테이너는 Configuration이 붙은 것을 스프링 설정 정보로 사용한다.
public class AppConfig { // 공연 기획자의 역할을 한다. -> 공연 참여자인 구현 객체들을 모두 알아야 한다. 

    @Bean   // 빈의 이름은 클래스 명 memberService
    public MemberService memberService(){ // 객체의 생성과 연결을 모두 담당한다.

        return new MemberServiceImpl(memberRepository()); // 의존 관계 주입 (Dependency Injection)
    }

    @Bean
    public MemberRepository memberRepository() {  // 객체 생성의 중복을 제거!
        // 구체 클래스 생성
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        //MemberRepository 와 DiscountPolicy가 필요하다.
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){ // 구체 클래스 생성 -> '구체 클래스 선택'과 '의존관계 주입'을 구분했다.
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
