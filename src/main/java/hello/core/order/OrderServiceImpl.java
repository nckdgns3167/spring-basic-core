package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    // 문제점1: 추상화, 구체화 모두 의존하고 있는 모습. (DIP 위반)
    // 문제점2: 할인 정책의 변경으로 인한 클라이언트 코드 변경(Fix -> Rate) 불가피 (OCP 위반)
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override

    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // SRP이 잘 지켜진 설계
        // 할인과 관련된 것은 discountPolicy가 알아서 할 일이고 결과만 받도록 되어 있음.
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
