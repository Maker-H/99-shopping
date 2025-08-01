package kr.hhplus.be.server.order.infra;

import kr.hhplus.be.server.order.domain.OrderEntity;
import kr.hhplus.be.server.order.domain.OrderItemEntity;
import kr.hhplus.be.server.point.domain.UserPointEntity;
import kr.hhplus.be.server.user.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, Long>{
    Optional<OrderItemEntity> findById(Long orderItemId);
}
