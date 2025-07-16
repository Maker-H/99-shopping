package kr.hhplus.be.server.product.dto;


public record GetProductResponse(
        Long productId,
        String productName,
        Long price,
        Long remainStock
) {

}
