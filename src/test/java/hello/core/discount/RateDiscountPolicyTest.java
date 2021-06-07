package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10%할인이 적용되어야 한다. ")
    void vip_ok(){
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        //when : vip가 10000원 주문 시, 할인 금액
        int discount = rateDiscountPolicy.discount(member, 10000);

        //then
        Assertions.assertThat(discount).isEqualTo(1000);

    }
    // 테스트 작성 시, 실패 테스트도 반드시 함께 작성해야 한다! (VIP가 아닌 경우!)
    @Test
    @DisplayName("VIP가 아니면, 10%할인이 적용이 안 된다.")
    void vip_fail(){
        //given
        Member member = new Member(1L, "memberbBASIC", Grade.BASIC);

        //when : vip가 10000원 주문 시, 할인 금액
        int discount = rateDiscountPolicy.discount(member, 10000);

        //then
        Assertions.assertThat(discount).isEqualTo(1000);

    }

}