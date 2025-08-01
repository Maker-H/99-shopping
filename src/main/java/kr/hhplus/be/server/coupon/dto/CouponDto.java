package kr.hhplus.be.server.coupon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.hhplus.be.server.coupon.domain.CouponEntity;
import kr.hhplus.be.server.coupon.domain.CouponEntity.CouponStatus;

import java.time.Instant;

public record CouponDto (
        @Schema(description = "사용자 ID", example = "42")
        Long userId,

        @Schema(description = "쿠폰 ID", example = "1001")
        Long couponId,

        @Schema(description = "쿠폰 상태", example = "ISSUED")
        CouponStatus couponStatus,

        @Schema(description = "쿠폰이 할인해주는 금액", example = "1000")
        Long discountAmount,

        @Schema(description = "쿠폰 만료 일시(UTC)", example = "2025-07-20T23:59:59Z")
        Instant expiredAt
) {

}
