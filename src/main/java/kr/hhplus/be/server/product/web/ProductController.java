package kr.hhplus.be.server.product.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.common.ApiResponse;
import kr.hhplus.be.server.product.application.ProductService;
import kr.hhplus.be.server.product.application.dto.GetProductResponse;
import kr.hhplus.be.server.product.application.dto.GetTop5ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Tag(name = "상품", description = "상품 관련 API")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    @Operation(summary = "상품 조회", description = "상품 ID로 상품을 조회")
    public ApiResponse<GetProductResponse> getProduct(
            @Parameter(description = "상품 ID", example = "123")
            @PathVariable("id") Long productId
    ) {

        GetProductResponse response = productService.getProduct(productId);
        return ApiResponse.success(response);
    }

    @GetMapping("/top5")
    @Operation(
            summary = "최근 3일간 판매량 상위 5개 상품 조회",
            description = "최근 3일 동안 판매량이 가장 높은 상위 5개 상품 목록을 반환"
    )
    public ApiResponse<List<GetTop5ProductResponse>> getTop5Products() {

        List<GetTop5ProductResponse> response = productService.getTop5Products();

        return ApiResponse.success(response);
    }
}
