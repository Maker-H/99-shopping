package kr.hhplus.be.server.point;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.common.ApiResponse;
import kr.hhplus.be.server.point.dto.ChargePointRequest;
import kr.hhplus.be.server.point.dto.ChargePointResponse;
import kr.hhplus.be.server.point.dto.GetPointResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/point")
@Tag(name = "포인트", description = "포인트 관련 API")
public class PointController {

    @PatchMapping("/charge")
    @Operation(summary = "포인트 충전", description = "사용자의 포인트를 특정 금액만큼 충전")
    public ApiResponse<ChargePointResponse> chargePoint(
            @Parameter(description = "포인트 충전 요청 정보", required = true)
            @RequestBody ChargePointRequest request
    ) {

        Long userId = request.userId();
        Long amount = request.amount();

        return ApiResponse.success(new ChargePointResponse(userId, amount));
    }

    @GetMapping
    @Operation(summary = "포인트 조회", description = "사용자의 보유 포인트를 조회")
    public ApiResponse<GetPointResponse> getPoint(
            @Parameter(description = "조회할 사용자 ID", example = "42")
            @RequestParam("userId") Long userId
    ) {
        Long amount = 200L;
        return ApiResponse.success(new GetPointResponse(userId, amount));
    }
}
