package hello.core.member;

// 어떤 인터페이스에 대한 구현체가 딱 하나일 경우에는 그 구현체 이름 뒤에 Impl을 붙여준다.
public class MemberServiceImpl implements MemberService {

    // 관심사를 분리하자.
    // 생성자 주입으로 DIP를 지키도록 변경했음. 👉 추상화에만 의존, 구현체 코드가 사라짐.
    // 이제 의존 관계에 대한 고민은 외부(AppConfig)에 맡기고, "실행에만 집중"하면 된다.
    // = Dependency Injection (DI)
    private final MemberRepository memberRepository;

    // 설계 변경으로 MemberServiceImpl 은 MemoryMemberRepository 를 의존하지 않는다.
    // 단지 MemberRepository 인터페이스(추상화, 역할)에만 의존한다.
    // 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
    // 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부에서 결정한다.
    // 이제부터 "의존관계에 대한 고민은 외부"에 맡기고 "실행에만 집중"하면 된다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member fineMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
