package honey.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository ;

    // 생성자 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    // methods들이 실행 될 껍데기 생성 -> 만들어 놓은 MemoryRepository로
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
