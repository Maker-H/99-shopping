package kr.hhplus.be.server.order.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.hhplus.be.server.coupon.domain.CouponId;
import kr.hhplus.be.server.user.domain.UserId;

import java.util.List;

public record CreateOrderRequest (

        @Schema(description = "사용자 ID", example = "42")
        Long userId,

        @Schema(description = "사용자가 사용할 쿠폰 ID", example = "42")
        Long couponId,

        List<OrderItemDto> orderItems

) {

        public UserId asUserId() {
                return new UserId(userId);
        }

        public CouponId asCouponId() {
                return new CouponId(couponId);
        }
}
