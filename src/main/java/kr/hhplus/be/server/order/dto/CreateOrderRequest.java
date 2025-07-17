package kr.hhplus.be.server.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record CreateOrderRequest (

        @Schema(description = "사용자 ID", example = "42")
        Long userId,

        @Schema(description = "사용자가 사용할 쿠폰 ID", example = "42")
        Long couponId,

        List<OrderItemDto> products

) {
}
