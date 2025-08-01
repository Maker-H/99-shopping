package kr.hhplus.be.server.order.infra;

import kr.hhplus.be.server.order.domain.OrderEntity;
import kr.hhplus.be.server.point.domain.UserPointEntity;
import kr.hhplus.be.server.user.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long>{
    Optional<UserPointEntity> findByUserId(UserId userId);
}
