package kr.hhplus.be.server.point.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.hhplus.be.server.common.ApiResponse;
import kr.hhplus.be.server.point.application.PointService;
import kr.hhplus.be.server.user.domain.UserId;
import kr.hhplus.be.server.point.application.dto.ChargePointRequest;
import kr.hhplus.be.server.point.application.dto.ChargePointResponse;
import kr.hhplus.be.server.point.application.dto.GetPointResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/point")
@RequiredArgsConstructor
@Tag(name = "포인트", description = "포인트 관련 API")
public class PointController {

    private final PointService pointService;

    @PatchMapping("/charge")
    @Operation(summary = "포인트 충전", description = "사용자의 포인트를 특정 금액만큼 충전")
    public ApiResponse<ChargePointResponse> chargePoint(
            @Parameter(description = "포인트 충전 요청 정보", required = true)
            @RequestBody @Valid ChargePointRequest command
    ) {

        ChargePointResponse response = pointService.chargePoint(command);

        return ApiResponse.success(response);
    }

    @GetMapping
    @Operation(summary = "포인트 조회", description = "사용자의 보유 포인트를 조회")
    public ApiResponse<GetPointResponse> getPoint(
            @Parameter(description = "조회할 사용자 ID", example = "42")
            @RequestParam("userId") Long userId
    ) {

        GetPointResponse response = pointService.getPoint(new UserId(userId));


        return ApiResponse.success(response);
    }
}
