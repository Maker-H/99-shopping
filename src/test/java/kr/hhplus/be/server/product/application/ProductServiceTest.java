package kr.hhplus.be.server.product.application;

import kr.hhplus.be.server.common.exception.ErrorType;
import kr.hhplus.be.server.product.application.dto.GetProductResponse;
import kr.hhplus.be.server.product.application.exception.NoSuchProductException;
import kr.hhplus.be.server.product.domain.ProductDetail;
import kr.hhplus.be.server.product.domain.ProductEntity;
import kr.hhplus.be.server.product.infra.ProductJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductJpaRepository repository;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("상품 ID로 상품을 성공적으로 조회한다")
    void getProduct_Success() {
        Long productId = 1L;
        ProductEntity productEntity = new ProductEntity(new ProductDetail("풍선껌", 1000L, 10L, 20L));

        when(repository.findById(productId)).thenReturn(Optional.of(productEntity));

        GetProductResponse response = productService.getProduct(productId);

        assertThat(response.productName()).isEqualTo("풍선껌");
        assertThat(response.price()).isEqualTo(1000L);
        verify(repository).findById(productId);
    }

    @Test
    @DisplayName("존재하지 않는 상품 ID로 조회 시 예외가 발생")
    void getProduct_NotFound() {
        Long productId = 999L;
        when(repository.findById(productId)).thenReturn(Optional.empty());

        NoSuchProductException noSuchProductException = assertThrows(
                NoSuchProductException.class,
                () -> productService.getProduct(productId)
        );

        assertEquals(ErrorType.NO_SUCH_PRODUCT, noSuchProductException.getErrorType());

        verify(repository).findById(productId);
    }
}