package kr.hhplus.be.server.point.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "포인트 충전 요청")
public record ChargePointRequest (

        @Schema(description = "사용자 ID", example = "42")
        Long userId,

        @Schema(description = "충전할 포인트 금액", example = "1000")
        Long amount
) {

}
