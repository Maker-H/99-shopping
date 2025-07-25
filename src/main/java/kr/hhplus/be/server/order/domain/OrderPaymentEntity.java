package kr.hhplus.be.server.order.domain;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_payment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderPaymentEntity extends BaseTimeEntity {

    @Id
    @Column(name = "order_payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderPaymentId;

    @JoinColumn(name = "order_id")
    @OneToOne(fetch = FetchType.LAZY)
    private OrderEntity order;

    @Column(name = "coupon_id")
    private Long couponId;

    private Long discountAmount;

    private Long totalAmount;

}

