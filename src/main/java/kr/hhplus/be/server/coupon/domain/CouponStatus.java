package kr.hhplus.be.server.coupon.domain;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "쿠폰 상태", example = "ISSUED", allowableValues = {"ISSUED", "USED", "EXPIRED"})
public enum CouponStatus {
    ISSUED, USED, EXPIRED
}
