package kr.hhplus.be.server.coupon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponId {

    @Column(name = "coupon_id", nullable = false)
    private Long value;

}