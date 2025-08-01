package kr.hhplus.be.server.product.domain;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Embedded
    private ProductDetail detail;

    public ProductEntity(ProductDetail detail) {
        this.detail = detail;
    }

    public boolean hasEnoughQuantity(Long quantity) {
        return this.detail.getCurrentStock() >= quantity;
    }

}
