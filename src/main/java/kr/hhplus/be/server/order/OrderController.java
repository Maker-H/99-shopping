package kr.hhplus.be.server.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import kr.hhplus.be.server.common.ApiResponse;
import kr.hhplus.be.server.order.domain.OrderStatus;
import kr.hhplus.be.server.order.dto.CreateOrderRequest;
import kr.hhplus.be.server.order.dto.CreateOrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/order")
@Tag(name = "주문", description = "주문 관련 API")
public class OrderController {

    @PostMapping
    @Operation(summary = "주문 생성", description = "주문을 생성")
    public ResponseEntity<ApiResponse<CreateOrderResponse>> createOrder(
            @Parameter(name = "txKey", description = "멱등 키", required = true, in = ParameterIn.HEADER)
            @RequestHeader String txKey,
            @Parameter(description = "주문 생성 요청 정보", required = true)
            @RequestBody CreateOrderRequest request
    ) {

        CreateOrderResponse response = new CreateOrderResponse(
                request.userId(),
                1L,
                1L,
                OrderStatus.SUCCESS,
                1_000L,
                10_000L
        );

        return ApiResponse.successWithHeader(response, Map.of("txKey", txKey));
    }
}
