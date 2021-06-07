package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 추상화에 의존. DiscountPolicy, 구현체에도 의존. FixDiscountPolicy.
    // --> 인터페이스에만(추상화에만) 의존해야하는데, 구현체에도 의존하고 있음.
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다! 따라서 OCP를 위반한다.

    // 인터페이스만 의존하도록 바꾼 결과. 하지만, 이 코드는 null point exception 에러 발생!
    private DiscountPolicy discountPolicy;
    // -> 이 문제를 해결하려면 누군가가 클라이언트인 OrderServiceImpl 에 DiscountPolicy 의 구현 객체를 대신 생성하고 주입해주어야 한다!

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //멤버 찾기
        Member member = memberRepository.findById(memberId);
        //멤버 grade에 따른 할인 금액 계산 (할인 프로세스를 분리해놨기 때문에 단일책임원칙을 잘 지킨 설계 사례다.)
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // Order 생성
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
