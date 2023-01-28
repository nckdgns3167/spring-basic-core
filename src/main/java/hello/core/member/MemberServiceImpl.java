package hello.core.member;

// 어떤 인터페이스에 대한 구현체가 딱 하나일 경우에는 그 구현체 이름 뒤에 Impl을 붙여준다.
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member fineMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
