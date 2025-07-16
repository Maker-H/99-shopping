package kr.hhplus.be.server.coupon;

import kr.hhplus.be.server.common.ApiResponse;
import kr.hhplus.be.server.coupon.domain.CouponStatus;
import kr.hhplus.be.server.coupon.dto.CouponDto;
import kr.hhplus.be.server.coupon.dto.GetCouponsResponse;
import kr.hhplus.be.server.coupon.dto.IssueCouponRequest;
import kr.hhplus.be.server.coupon.dto.IssueCouponResponse;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @GetMapping
    public ApiResponse<GetCouponsResponse> getCoupons(@RequestParam("userId") Long userId) {

        CouponDto firstCoupon = new CouponDto(userId,
                1L,
                CouponStatus.ISSUED,
                1000L,
                Instant.now().plus(2, ChronoUnit.DAYS)
        );

        CouponDto secondCoupon = new CouponDto(userId,
                2L,
                CouponStatus.ISSUED,
                1000L,
                Instant.now().plus(2, ChronoUnit.DAYS)
        );

        GetCouponsResponse response = new GetCouponsResponse(List.of(firstCoupon, secondCoupon));

        return ApiResponse.success(response);
    }

    @PostMapping("/issue")
    public ApiResponse<IssueCouponResponse> issueCoupon(@RequestBody IssueCouponRequest request) {

        IssueCouponResponse response = new IssueCouponResponse(
                request.userId(),
                1L,
                CouponStatus.ISSUED,
                1000L,
                Instant.now().plus(2, ChronoUnit.DAYS)

        );

        return ApiResponse.success(response);
    }
}
