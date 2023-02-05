package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    // 관심사를 분리하자.
    // 추상화, 구체화 모두 의존하는 코드에서 생성자 주입으로 DIP를 지키도록 변경했음. 👉 추상화에만 의존, 구현체 코드가 사라짐.
    // 이제 의존 관계에 대한 고민은 외부(AppConfig)에 맡기고, "실행에만 집중"하면 된다.
    // = Dependency Injection (DI)
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    // 설계 변경으로 OrderServiceImpl 는 FixDiscountPolicy(구현체) 에 의존하지 않는다.
    // 단지 discountPolicy 인터페이스(추상화, 역할)에만 의존한다.
    // 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
    // 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부에서 결정한다.
    // 이제부터 "의존관계에 대한 고민은 외부"에 맡기고 "실행에만 집중"하면 된다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override

    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // SRP이 잘 지켜진 설계
        // 할인과 관련된 것은 discountPolicy가 알아서 할 일이고 결과만 받도록 되어 있음.
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
