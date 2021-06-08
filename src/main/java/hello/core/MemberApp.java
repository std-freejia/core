package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) { // 이런식으로 테스트 하지 말고, test코드 작성하기!

        /* 스프링 전환 이전
        AppConfig appConfig = new AppConfig();
        // 의존관계 주입 이전 코드(구현 클래스를 명시) : MemberService memberService = new MemberServiceImpl();
        MemberService memberService = appConfig.memberService();
        // 의존관계 주입 이후 -> MemberServiceImpl() 객체 생성하면서 MemoryMemberRepository() 선택하여 연결한 채로 객체를 돌려준다.
        */

        // 스프링으로 전환 이후
        // AppConfig의 설정 정보대로 스프링 컨테이너 applicationContext에 스프링빈에 등록한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // MemberService 빈을 memberService 이름으로 MemberService.class 타입의 객체를 꺼낸다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        // 제대로 저장이 되었는지 memberA 와 findmember를 비교한다.
        Member findmember = memberService.findMember(1L);
        System.out.println("new member = " + memberA.getName());
        System.out.println("find member = " + findmember.getName());

    }
}
