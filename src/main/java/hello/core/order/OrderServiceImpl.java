package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

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
