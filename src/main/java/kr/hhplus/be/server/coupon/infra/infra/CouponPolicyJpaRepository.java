package kr.hhplus.be.server.coupon.infra.infra;

import kr.hhplus.be.server.coupon.domain.CouponEntity;
import kr.hhplus.be.server.coupon.domain.CouponPolicyEntity;
import kr.hhplus.be.server.point.domain.UserPointEntity;
import kr.hhplus.be.server.user.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponPolicyJpaRepository extends JpaRepository<CouponPolicyEntity, Long>{
    Optional<CouponPolicyEntity> findById(Long couponId);
}
