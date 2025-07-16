package kr.hhplus.be.server.order.dto;

import kr.hhplus.be.server.order.domain.OrderStatus;

public record CreateOrderResponse (
        Long userId,
        Long orderId,
        Long orderPaymentId,
        OrderStatus orderStatus,
        Long discountAmount,
        Long totalChargedAmount
) {

}
