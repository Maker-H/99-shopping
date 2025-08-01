package kr.hhplus.be.server.order.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.hhplus.be.server.order.domain.OrderEntity;
import kr.hhplus.be.server.order.domain.OrderEntity.OrderStatus;

public record CreateOrderResponse (

        @Schema(description = "사용자 ID", example = "42")
        Long userId,

        @Schema(description = "주문 ID", example = "1001")
        Long orderId,

        @Schema(description = "주문 결제 ID", example = "5001")
        Long orderPaymentId,

        @Schema(description = "주문 상태", example = "SUCCESS")
        OrderStatus orderStatus,

        @Schema(description = "쿠폰이 할인해준 금액", example = "1000")
        Long discountAmount,

        @Schema(description = "실제 결제된 총 금액", example = "9000")
        Long totalChargedAmount

) {

}
