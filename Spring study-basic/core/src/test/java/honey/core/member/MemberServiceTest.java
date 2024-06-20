package honey.core.member;

import honey.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {


//    MemberService memberService = new MemberServiceImpl();

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //given -> ~가 주어지면

        Member member = new Member(1l,"member",Grade.VIP);

        //when -> ~할 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then -> ~하게 된다. org.assertj.core.api 사용하면된다.
        Assertions.assertThat(member).isEqualTo(findMember);  //true or false


    }
}
