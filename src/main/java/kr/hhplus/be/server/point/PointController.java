package kr.hhplus.be.server.point;

import kr.hhplus.be.server.common.ApiResponse;
import kr.hhplus.be.server.point.dto.ChargePointRequest;
import kr.hhplus.be.server.point.dto.ChargePointResponse;
import kr.hhplus.be.server.point.dto.GetPointResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/point")
public class PointController {

    @PatchMapping("/charge")
    public ApiResponse<ChargePointResponse> chargePoint(@RequestBody ChargePointRequest request) {

        Long userId = request.userId();
        Long amount = request.amount();

        return ApiResponse.success(new ChargePointResponse(userId, amount));
    }

    @GetMapping
    public ApiResponse<GetPointResponse> getPoint(@RequestParam("userId") Long userId) {
        Long amount = 200L;
        return ApiResponse.success(new GetPointResponse(userId, amount));
    }
}
