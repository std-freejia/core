package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    /* 의존관계 주입 이전 : 구현체에 의존
    MemberService memberService = new MemberServiceImpl();
    */

    // 의존관계 주입 이후 : 생성자를 이용하여 인터페이스에만 의존  appConfig.memberService();
    MemberService memberService;

    @BeforeEach // 각 테스트 실행 전에 무조건 실행 되는 메소드.
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

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
