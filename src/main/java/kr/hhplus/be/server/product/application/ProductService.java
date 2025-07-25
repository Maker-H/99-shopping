package kr.hhplus.be.server.product.application;

import kr.hhplus.be.server.product.infra.Top5ProductProjection;
import kr.hhplus.be.server.product.application.dto.GetTop5ProductResponse;
import kr.hhplus.be.server.product.application.mapper.ProductMapper;
import kr.hhplus.be.server.product.domain.ProductEntity;
import kr.hhplus.be.server.product.application.dto.GetProductResponse;
import kr.hhplus.be.server.product.application.exception.NoSuchProductException;
import kr.hhplus.be.server.product.infra.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kr.hhplus.be.server.common.exception.ErrorType.NO_SUCH_PRODUCT;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductJpaRepository repository;

    public GetProductResponse getProduct(Long productId) {
        ProductEntity found = repository.findById(productId)
                .orElseThrow(() -> new NoSuchProductException("productId: " + productId + "를 찾을 수 없습니다"));

        return ProductMapper.toGetProductResponse(found);
    }

    @Transactional(readOnly = true)
    public List<GetTop5ProductResponse> getTop5Products() {
        List<Top5ProductProjection> projections = repository.findTop5ProductsByRecentSales();

        return projections.stream()
                .map(p -> new GetTop5ProductResponse(
                        p.getProductId(),
                        p.getName(),
                        p.getPrice(),
                        p.getSalesCount(),
                        p.getRank()
                ))
                .toList();
    }
}
