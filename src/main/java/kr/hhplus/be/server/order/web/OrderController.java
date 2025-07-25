package kr.hhplus.be.server.order.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import kr.hhplus.be.server.common.ApiResponse;
import kr.hhplus.be.server.order.application.dto.OrderService;
import kr.hhplus.be.server.order.application.dto.CreateOrderRequest;
import kr.hhplus.be.server.order.application.dto.CreateOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Tag(name = "주문", description = "주문 관련 API")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "주문", description = "주문 생성 후 결제")
    public ResponseEntity<ApiResponse<CreateOrderResponse>> processOrder(
            @Parameter(name = "txKey", description = "멱등 키", required = true, in = ParameterIn.HEADER)
            @RequestHeader String txKey,
            @Parameter(description = "주문 생성 요청 정보", required = true)
            @RequestBody CreateOrderRequest request
    ) {

        CreateOrderResponse response = orderService.processOrder(txKey, request);

        // TODO: 멱등키 처리

        return ApiResponse.successWithHeader(response, Map.of("txKey", txKey));
    }
}
