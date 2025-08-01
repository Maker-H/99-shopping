package kr.hhplus.be.server.coupon.domain;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.BaseTimeEntity;
import kr.hhplus.be.server.point.domain.Amount;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "coupon_policy")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponPolicyEntity extends BaseTimeEntity {

    @Id
    @Column(name = "coupon_policy_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponPolicyId;

    @Column(name = "discount_amount", nullable = false)
    private DiscountAmount discountAmount;

    private Long totalQuantity;

    private Long remainingQuantity;

    private Instant expiredAt;

}

