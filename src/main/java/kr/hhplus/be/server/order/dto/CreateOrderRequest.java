package kr.hhplus.be.server.order.dto;

import java.util.List;

public record CreateOrderRequest (
        Long userId,
        Long couponId,
        List<OrderItemDto> products
) {
}
