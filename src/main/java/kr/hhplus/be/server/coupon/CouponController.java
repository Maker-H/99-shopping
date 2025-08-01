package kr.hhplus.be.server.coupon;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.common.ApiResponse;
import kr.hhplus.be.server.coupon.domain.CouponEntity;
import kr.hhplus.be.server.coupon.domain.CouponEntity.CouponStatus;
import kr.hhplus.be.server.coupon.dto.CouponDto;
import kr.hhplus.be.server.coupon.dto.GetCouponsResponse;
import kr.hhplus.be.server.coupon.dto.IssueCouponRequest;
import kr.hhplus.be.server.coupon.dto.IssueCouponResponse;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static kr.hhplus.be.server.coupon.domain.CouponEntity.CouponStatus.*;


@RestController
@RequestMapping("/api/coupon")
@Tag(name = "쿠폰", description = "쿠폰 관련 API")
public class CouponController {

    @GetMapping
    @Operation(summary = "사용자 쿠폰 목록 조회", description = "사용자의 ID로 보유 중인 쿠폰들을 조회")
    public ApiResponse<GetCouponsResponse> getCoupons(
            @Parameter(description = "사용자 ID", example = "42")
            @RequestParam("userId") Long userId
    ) {

        CouponDto firstCoupon = new CouponDto(userId,
                1L,
                ISSUED,
                1000L,
                Instant.now().plus(2, ChronoUnit.DAYS)
        );

        CouponDto secondCoupon = new CouponDto(userId,
                2L,
                ISSUED,
                1000L,
                Instant.now().plus(2, ChronoUnit.DAYS)
        );

        GetCouponsResponse response = new GetCouponsResponse(List.of(firstCoupon, secondCoupon));

        return ApiResponse.success(response);
    }

    @PostMapping("/issue")
    @Operation(summary = "쿠폰 발급", description = "사용자 ID를 기반으로 쿠폰을 발급")
    public ApiResponse<IssueCouponResponse> issueCoupon(
            @Parameter(description = "쿠폰 발급 요청 정보", required = true)
            @RequestBody IssueCouponRequest request
    ) {

        IssueCouponResponse response = new IssueCouponResponse(
                request.userId(),
                1L,
                ISSUED,
                1000L,
                Instant.now().plus(2, ChronoUnit.DAYS)

        );

        return ApiResponse.success(response);
    }
}
