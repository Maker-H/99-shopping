package kr.hhplus.be.server.coupon.dto;

import kr.hhplus.be.server.coupon.domain.CouponStatus;

import java.time.Instant;
import java.time.ZonedDateTime;

public record IssueCouponResponse (
        Long userId,
        Long couponId,
        CouponStatus couponStatus,
        Long discountAmount,
        Instant expiredAt
) {
}
