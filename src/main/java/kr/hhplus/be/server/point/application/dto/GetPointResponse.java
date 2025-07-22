package kr.hhplus.be.server.point.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetPointResponse (
        @Schema(description = "사용자 ID", example = "42")
        Long userId,

        @Schema(description = "현재 포인트 금액", example = "1000")
        Long currentPoint
) {

}
