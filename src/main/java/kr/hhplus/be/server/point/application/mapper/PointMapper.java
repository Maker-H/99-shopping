package kr.hhplus.be.server.point.application.mapper;

import kr.hhplus.be.server.point.domain.UserPointEntity;
import kr.hhplus.be.server.point.application.dto.ChargePointResponse;
import kr.hhplus.be.server.point.application.dto.GetPointResponse;

public class PointMapper {

    public static ChargePointResponse toChargePointResponse(UserPointEntity entity) {
        return new ChargePointResponse(entity.getUserId().getValue(), entity.getPointAmount().getValue());
    }

    public static GetPointResponse toGetPointResponse(UserPointEntity entity) {
        return new GetPointResponse(entity.getUserId().getValue(), entity.getPointAmount().getValue());
    }
}
