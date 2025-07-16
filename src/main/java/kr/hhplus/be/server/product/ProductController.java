package kr.hhplus.be.server.product;

import kr.hhplus.be.server.common.ApiResponse;
import kr.hhplus.be.server.product.dto.GetProductResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @GetMapping("/{id}")
    public ApiResponse<GetProductResponse> getProduct(@PathVariable("id") Long productId) {

        GetProductResponse response = new GetProductResponse(
                productId,
                "풍선껌",
                1000L,
                10L
        );

        return ApiResponse.success(response);
    }
}
