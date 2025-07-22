package kr.hhplus.be.server.point.domain;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.BaseTimeEntity;
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

    @Embedded
    private Amount pointAmount;

    private UserPointEntity(UserId userId, Amount pointAmount) {
        this.userId = userId;
        this.pointAmount = pointAmount;
    }

    public static UserPointEntity empty(UserId userId) {
        return new UserPointEntity(userId, Amount.zero());
    }

    public void charge(Amount amount) {
        this.pointAmount.add(amount);
    }
}

