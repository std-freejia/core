package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int DiscountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) { // VIP인 경우에만 1000원 할인.
        if(member.getGrade() == Grade.VIP){ // enum 은 ==으로 비교하는 것이 맞습니다.
            return DiscountFixAmount;
        }else {
            return 0;
        }
    }
}
