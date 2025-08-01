package kr.hhplus.be.server.coupon.dto;


import io.swagger.v3.oas.annotations.media.Schema;

public record IssueCouponRequest (

        @Schema(description = "사용자 ID", example = "42")
        Long userId,

        @Schema(description = "발급받을 쿠폰 정책 ID", example = "101")
        Long CouponPolicyId
) {

}
