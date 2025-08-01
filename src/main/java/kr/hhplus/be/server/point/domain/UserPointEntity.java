package kr.hhplus.be.server.point.domain;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.BaseTimeEntity;
import kr.hhplus.be.server.coupon.domain.DiscountAmount;
import kr.hhplus.be.server.user.domain.UserId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_point")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPointEntity extends BaseTimeEntity {

    @Id
    @Column(name = "point_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserId userId;

    @AttributeOverride(name = "value", column = @Column(name = "point_amount", nullable = false))
    private Amount pointAmount;

    private UserPointEntity(UserId userId, Amount pointAmount) {
        this.userId = userId;
        this.pointAmount = pointAmount;
    }

    public static UserPointEntity empty(UserId userId) {
        return new UserPointEntity(userId, Amount.zero());
    }

    public Amount charge(Amount amount) {
        Amount chargedAmount = this.pointAmount.add(amount);
        this.pointAmount = chargedAmount;
        return chargedAmount;
    }

    public boolean isSufficient(DiscountAmount discountAmount) {
        return this.pointAmount.getValue() >= discountAmount.getValue();
    }

    public Amount use(DiscountAmount discountAmount) {
        Amount subtractedAmount = this.pointAmount.use(discountAmount);
        this.pointAmount = subtractedAmount;
        return subtractedAmount;
    }
}

