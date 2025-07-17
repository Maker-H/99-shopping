package kr.hhplus.be.server.order.domain;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "주문 상태",
        allowableValues = {"CREATED", "BEFORE_PAYMENT", "SUCCESS", "CANCELED"},
        example = "SUCCESS"
)
public enum OrderStatus {
    CREATED, BEFORE_PAYMENT, SUCCESS, CANCELED
}
