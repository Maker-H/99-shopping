package kr.hhplus.be.server.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDetail {

    @Column(name = "name", nullable = false, length = 255)
    private String productName;

    @Column(name = "price", nullable = false)
    private Long productPrice;

    @Column(name = "current_stock", nullable = false)
    private Long currentStock;

    @Column(name = "total_stock", nullable = false)
    private Long totalStock;

}