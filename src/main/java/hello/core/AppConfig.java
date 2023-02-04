package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 공연 기획자 역할, "구성 영역"
// 이 클래스의 역할은 애플리케이션의 실제 동작에 필요한 "구현 객체를 생성"하는 것.
// 생성한 객체 인스턴스의 레퍼런스를 "생성자를 통해서 주입(연결)"한다.
@Configuration
public class AppConfig {

    // 역할과 구현 클래스가 한눈에 들어오도록 변경.
    // 👉 애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악 가능.

    // new MemoryMemberRepository() 코드 중복이 제거됐다.
    // 만약 다른 구현체로 스왑해야할 때 이 부분만 변경하면 된다.
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
// 할인 정책을 바꾸었지만 클라이언트 코드인 OrderServiceImpl 를
// 포함해서 "사용 영역"의 어떤 코드도 변경하지 않았다. "구성 영역"인 이곳만 변경했다.
// OCP를 지키게 됐다.
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
