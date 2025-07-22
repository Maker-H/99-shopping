package kr.hhplus.be.server.product.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "최근 3일간 판매량 상위 5개 상품 조회 응답")
public record GetTop5ProductResponse(

        @Schema(description = "상품 ID", example = "123")
        Long productId,

        @Schema(description = "상품 이름", example = "풍선껌")
        String productName,

        @Schema(description = "상품 가격", example = "1000")
        Long price,

        @Schema(description = "판매량", example = "50")
        Integer salesCount,

        @Schema(description = "순위", example = "1")
        Integer rank

) {
}