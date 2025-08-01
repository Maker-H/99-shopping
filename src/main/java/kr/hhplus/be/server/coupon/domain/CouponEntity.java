package kr.hhplus.be.server.coupon.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import kr.hhplus.be.server.user.domain.UserId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "coupon")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponEntity {

    @Id
    @Column(name = "coupon_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;

    @Embedded
    private UserId userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_policy_id")
    private CouponPolicyEntity couponPolicy;

    private Instant issuedAt;

    @Enumerated(EnumType.STRING)
    private CouponStatus status;


    @Schema(
            description = "쿠폰 상태",
            example = "ISSUED",
            allowableValues = {"ISSUED", "USED", "EXPIRED"}
    )
    public enum CouponStatus {
        ISSUED, USED, EXPIRED
    }

    public boolean isNotUsable() {
        if (this.status == CouponStatus.EXPIRED || this.status == CouponStatus.USED) {
            return false;
        }

        return true;
    }

}
