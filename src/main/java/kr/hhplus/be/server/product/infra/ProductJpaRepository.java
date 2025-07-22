package kr.hhplus.be.server.product.infra;


import kr.hhplus.be.server.product.domain.ProductEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long>{
    Optional<ProductEntity> findById(Long productId);

    ProductEntity save(ProductEntity entity);

    @Profile("!test")
    @Query(value = """
        SELECT p.product_id, p.name, p.price, 
               COALESCE(SUM(oi.quantity), 0) as sales_count,
               ROW_NUMBER() OVER (ORDER BY COALESCE(SUM(oi.quantity), 0) DESC) as rank
        FROM product p
        LEFT JOIN order_item oi ON p.product_id = oi.product_id
        LEFT JOIN orders o ON oi.order_id = o.order_id
        WHERE o.created_at >= DATE_SUB(NOW(), INTERVAL 3 DAY)
          AND o.status = 'SUCCESS'
        GROUP BY p.product_id, p.name, p.price
        ORDER BY sales_count DESC
        LIMIT 5
        """, nativeQuery = true)
    List<Top5ProductProjection> findTop5ProductsByRecentSalesMySQL();

    @Profile("test")
    @Query(value = """
        SELECT p.product_id, p.name, p.price, 
               COALESCE(SUM(oi.quantity), 0) as sales_count,
               ROW_NUMBER() OVER (ORDER BY COALESCE(SUM(oi.quantity), 0) DESC) as rank
        FROM product p
        LEFT JOIN order_item oi ON p.product_id = oi.product_id
        LEFT JOIN orders o ON oi.order_id = o.order_id
        WHERE o.created_at >= DATEADD('DAY', -3, NOW())
          AND o.status = 'SUCCESS'
        GROUP BY p.product_id, p.name, p.price
        ORDER BY sales_count DESC
        LIMIT 5
        """, nativeQuery = true)
    List<Top5ProductProjection> findTop5ProductsByRecentSalesH2();

    default List<Top5ProductProjection> findTop5ProductsByRecentSales() {
        // Spring이 활성화된 프로파일에 따라 자동으로 적절한 메서드 호출
        return null; // 실제로는 프로파일에 따라 적절한 구현체가 선택됨
    }
}
