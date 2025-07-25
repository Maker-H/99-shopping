package kr.hhplus.be.server.point.domain;

import jakarta.persistence.Embeddable;
import kr.hhplus.be.server.coupon.domain.DiscountAmount;
import lombok.*;

@Getter @ToString
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Amount {

    private Long value;

    public static Amount zero() {
        return new Amount(0L);
    }

    public Amount add(Amount other) {
        return new Amount(this.value + other.getValue());
    }

    public Amount use(Amount other) {
        return new Amount(this.value - other.getValue());
    }

    public Amount use(DiscountAmount discountAmount) {
        return new Amount(this.value - discountAmount.getValue());
    }
}