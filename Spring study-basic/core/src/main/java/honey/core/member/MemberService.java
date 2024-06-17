package honey.core.member;

public interface MemberService {

    //MemberService 생성

    void join(Member member);

    Member findMember(Long memberId);
}
