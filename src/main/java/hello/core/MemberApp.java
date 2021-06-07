package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) { // 이런식으로 테스트 하지 말고, test코드 작성하기!

        AppConfig appConfig = new AppConfig();

        // 의존관계 주입 이전 코드(구현 클래스를 명시) : MemberService memberService = new MemberServiceImpl();
        MemberService memberService = appConfig.memberService();
        // 의존관계 주입 이후 -> MemberServiceImpl() 객체 생성하면서 MemoryMemberRepository() 선택하여 연결한 채로 객체를 돌려준다.

        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        // 제대로 저장이 되었는지 memberA 와 findmember를 비교한다.
        Member findmember = memberService.findMember(1L);
        System.out.println("new member = " + memberA.getName());
        System.out.println("find member = " + findmember.getName());

    }
}
