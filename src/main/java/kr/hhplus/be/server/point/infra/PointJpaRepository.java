package kr.hhplus.be.server.point.infra;

import kr.hhplus.be.server.user.domain.UserId;
import kr.hhplus.be.server.point.domain.UserPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointJpaRepository extends JpaRepository<UserPointEntity, Long>{
    Optional<UserPointEntity> findByUserId(UserId userId);
}
