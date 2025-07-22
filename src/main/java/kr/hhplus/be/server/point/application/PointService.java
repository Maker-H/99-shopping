package kr.hhplus.be.server.point.application;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.point.application.mapper.PointMapper;
import kr.hhplus.be.server.point.domain.Amount;
import kr.hhplus.be.server.point.domain.UserId;
import kr.hhplus.be.server.point.domain.UserPointEntity;
import kr.hhplus.be.server.point.application.dto.ChargePointRequest;
import kr.hhplus.be.server.point.application.dto.ChargePointResponse;
import kr.hhplus.be.server.point.application.dto.GetPointResponse;
import kr.hhplus.be.server.point.infra.PointJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointJpaRepository repository;

    @Transactional
    public ChargePointResponse chargePoint(ChargePointRequest command) {

        Amount amount = command.asAmount();
        UserId userId = command.asUserId();

        UserPointEntity found = repository.findByUserId(userId)
                .orElseGet(() -> UserPointEntity.empty(userId));

        found.charge(amount);

        repository.save(found);

        return PointMapper.toChargePointResponse(found);
    }

    @Transactional
    public GetPointResponse getPoint(UserId userId) {

        UserPointEntity found = repository.findByUserId(userId)
                .orElseGet(() -> UserPointEntity.empty(userId));

        repository.save(found);

        return PointMapper.toGetPointResponse(found);
    }


}