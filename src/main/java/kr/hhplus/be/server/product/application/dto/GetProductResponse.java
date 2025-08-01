package kr.hhplus.be.server.product.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "상품 조회 응답")
public record GetProductResponse(

        @Schema(description = "상품 ID", example = "123")
        Long productId,

        @Schema(description = "상품 이름", example = "풍선껌")
        String productName,

        @Schema(description = "상품 가격", example = "1000")
        Long price,

        @Schema(description = "남은 재고 수량", example = "10")
        Long remainStock

) {
}

