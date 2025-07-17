package kr.hhplus.be.server.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record OrderItemDto(
        @Schema(description = "사용자가 주문한 상품 ID", example = "10")
        Long productId,

        @Schema(description = "사용자가 주문한 상품 수량", example = "1")
        Long amount
) {
}
