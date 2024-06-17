package honey.core.discount;

import honey.core.member.Grade;
import honey.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
    //Ctrl + Shift + T 를 눌러서 테스트 케이스 만든다.
        if(member.getGrade() == Grade.VIP){
            return (price * discountPercent) / 100;
        }
        return 0;
    }
}
