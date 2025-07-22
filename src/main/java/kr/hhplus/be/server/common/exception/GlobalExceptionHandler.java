package kr.hhplus.be.server.common.exception;

import kr.hhplus.be.server.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<String>> handleCustomException(CustomException ex) {
        log.error("Custom exception occurred: {}", ex.getCustomMessage(), ex);
        return ResponseEntity.status(ex.getHttpStatus()).body(ApiResponse.fromCustomException(ex));
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiResponse<String>> handleAll(Throwable ex) {
        log.error("throwable caused", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.fromUnknownException());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException caused", ex);

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(FieldError::getDefaultMessage)
                .orElse("유효하지 않은 요청입니다.");

        return ResponseEntity.badRequest().body(ApiResponse.fromOtherException("INVALID_PARAMETER", errorMessage));
    }

}
