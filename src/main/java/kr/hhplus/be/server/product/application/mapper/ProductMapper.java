package kr.hhplus.be.server.product.application.mapper;

import kr.hhplus.be.server.product.domain.ProductDetail;
import kr.hhplus.be.server.product.domain.ProductEntity;
import kr.hhplus.be.server.product.application.dto.GetProductResponse;

public class ProductMapper {

    public static GetProductResponse toGetProductResponse(ProductEntity entity) {
        ProductDetail detail = entity.getDetail();
        return new GetProductResponse(
                entity.getId(),
                detail.getProductName(),
                detail.getProductPrice(),
                detail.getCurrentStock()
        );
    }
}
