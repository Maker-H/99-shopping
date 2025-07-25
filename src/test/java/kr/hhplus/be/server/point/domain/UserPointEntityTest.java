package kr.hhplus.be.server.point.domain;

import kr.hhplus.be.server.coupon.domain.DiscountAmount;
import kr.hhplus.be.server.user.domain.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPointEntityTest {

    @Test
    void 유저의_포인트가_부족() {
        UserPointEntity userPoint = get_100원을_가진_유저();

        Assertions.assertFalse(userPoint.isSufficient(new DiscountAmount(101L)));
    }

    @Test
    void 유저의_포인트가_충분() {
        UserPointEntity userPoint = get_100원을_가진_유저();

        Assertions.assertTrue(userPoint.isSufficient(new DiscountAmount(100L)));
    }

    private UserPointEntity get_100원을_가진_유저() {
        UserPointEntity user = UserPointEntity.empty(new UserId(1L));
        user.charge(new Amount(100L));
        return user;
    }
}