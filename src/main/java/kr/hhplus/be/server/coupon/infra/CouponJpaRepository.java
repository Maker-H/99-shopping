package kr.hhplus.be.server.coupon.infra;

import kr.hhplus.be.server.coupon.domain.CouponEntity;
import kr.hhplus.be.server.point.domain.UserPointEntity;
import kr.hhplus.be.server.user.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponJpaRepository extends JpaRepository<CouponEntity, Long>{
    Optional<UserPointEntity> findById(UserId userId);
}
