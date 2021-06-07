package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl(); // 구현체에 의존

    @Test
    void join(){
        // given
        Member member = new Member(1L, "member", Grade.VIP);
        // when
        memberService.join(member);
        Member findmember = memberService.findMember(1L);
        // then
        Assertions.assertThat(member).isEqualTo(findmember);
    }
}
