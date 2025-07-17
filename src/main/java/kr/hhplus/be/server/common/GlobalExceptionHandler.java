package kr.hhplus.be.server.common;

import kr.hhplus.be.server.coupon.CouponController;
import kr.hhplus.be.server.order.OrderController;
import kr.hhplus.be.server.point.PointController;
import kr.hhplus.be.server.product.ProductController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<String>> handleCustomException(CustomException ex) {
        log.error("Custom exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(ex.getHttpStatus()).body(ApiResponse.fromCustomException(ex));
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiResponse<String>> handleAll(Throwable ex) {
        log.error("throwable caused", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.fromUnknownException());
    }

}
