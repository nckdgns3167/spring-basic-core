package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;


// 공연 기획자 역할
// 이 클래스의 역할은 애플리케이션의 실제 동작에 필요한 "구현 객체를 생성"하는 것.
// 생성한 객체 인스턴스의 레퍼런스를 "생성자를 통해서 주입(연결)"한다.
public class AppConfig {

    // 역할과 구현 클래스가 한눈에 들어오도록 변경.
    // 👉 애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악 가능.

    // new MemoryMemberRepository() 코드 중복이 제거됐다.
    // 만약 다른 구현체로 스왑해야할 때 이 부분만 변경하면 된다.
    private static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    private static DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }


    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
