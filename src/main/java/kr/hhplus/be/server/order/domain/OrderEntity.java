package kr.hhplus.be.server.order.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import kr.hhplus.be.server.common.BaseTimeEntity;
import kr.hhplus.be.server.user.domain.UserId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity extends BaseTimeEntity {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Embedded
    private UserId userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItems = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private OrderPaymentEntity orderPayment;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Schema(
            description = "주문 상태",
            allowableValues = {"CREATED", "SUCCESS", "CANCELED"},
            example = "SUCCESS"
    )
    public enum OrderStatus {
        CREATED, SUCCESS, CANCELED
    }

    public void success() {
        this.status = OrderStatus.SUCCESS;
    }

    public void canceled() {
        this.status = OrderStatus.CANCELED;
    }

    public static OrderEntity createOrder(UserId userId) {
        return new OrderEntity(userId);
    }

    private OrderEntity(UserId userId) {
        this.userId = userId;
        this.status = OrderStatus.CREATED;
    }



}

