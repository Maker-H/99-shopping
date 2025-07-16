package kr.hhplus.be.server.coupon.dto;

import java.util.List;


public record GetCouponsResponse (
        List<CouponDto> coupons
){
}
