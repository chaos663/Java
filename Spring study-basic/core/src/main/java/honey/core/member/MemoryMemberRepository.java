package honey.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    // 여기는 구현 클래스

    // key-value 쌍으로 되어있는 HashMap 저장 공간 생성
    // 동시성 이슈 때문에 사실은 concurrent HashMap을 사용하는 것이 맞음
    // private  static Map<Long, Member> store = new ConcurrentHashMap<>();
    private  static Map<Long, Member> store = new HashMap<>();

    // save 구현
    @Override
    public void save(Member member) {
        store.put(member.getId(),member);
    }
    // 조회 구현
    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
