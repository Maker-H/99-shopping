package kr.hhplus.be.server.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.common.ApiResponse;
import kr.hhplus.be.server.product.dto.GetProductResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "상품", description = "상품 관련 API")
@RequestMapping("/api/product")
public class ProductController {

    @GetMapping("/{id}")
    @Operation(summary = "상품 조회", description = "상품 ID로 상품을 조회")
    public ApiResponse<GetProductResponse> getProduct(
            @Parameter(description = "상품 ID", example = "123")
            @PathVariable("id") Long productId
    ) {

        GetProductResponse response = new GetProductResponse(
                productId,
                "풍선껌",
                1000L,
                10L
        );

        return ApiResponse.success(response);
    }
}
