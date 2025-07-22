package kr.hhplus.be.server.point.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter @ToString
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Amount {

    @Column(name = "point_amount", nullable = false)
    private Long value;

    public static Amount zero() {
        return new Amount(0L);
    }

    public void add(Amount amount) {
        this.value += amount.getValue();
    }

}