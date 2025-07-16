package kr.hhplus.be.server.order;

import kr.hhplus.be.server.common.ApiResponse;
import kr.hhplus.be.server.order.domain.OrderStatus;
import kr.hhplus.be.server.order.dto.CreateOrderRequest;
import kr.hhplus.be.server.order.dto.CreateOrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @PostMapping
    public ResponseEntity<ApiResponse<CreateOrderResponse>> createOrder(
            @RequestHeader String txKey,
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
