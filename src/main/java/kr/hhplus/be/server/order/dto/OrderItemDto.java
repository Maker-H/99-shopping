package kr.hhplus.be.server.order.dto;

public record OrderItemDto(
        Long productId,
        Long amount
) {
}
