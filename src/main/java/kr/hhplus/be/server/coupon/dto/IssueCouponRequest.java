package kr.hhplus.be.server.coupon.dto;


public record IssueCouponRequest (
        Long userId,
        Long CouponPolicyId
) {

}
