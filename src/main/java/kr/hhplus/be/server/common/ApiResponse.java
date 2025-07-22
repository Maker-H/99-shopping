package kr.hhplus.be.server.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Map;

public record ApiResponse<T> (
        String clientCode,
        T data,
        Instant timeStamp
){

    private static final String SUCCESS_CODE = "SUCCESS";
    private static final String UNKNOWN_ERROR_CODE = "UNKNOWN_ERROR";

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(SUCCESS_CODE, data, Instant.now());
    }

    public static ApiResponse<String> fromOtherException(String clientCode, String exceptionMsg) {
        return new ApiResponse<>(clientCode, exceptionMsg, Instant.now());
    }

    public static ApiResponse<String> fromCustomException(CustomException customException) {
        return new ApiResponse<>(customException.getClientCode(), "", Instant.now());
    }

    public static ApiResponse<String> fromUnknownException() {
        return new ApiResponse<>(UNKNOWN_ERROR_CODE, "", Instant.now());
    }

    public static <T> ResponseEntity<ApiResponse<T>> successWithHeader(T data, Map<String, String> headers) {
        ApiResponse<T> body = new ApiResponse<>(SUCCESS_CODE, data, Instant.now());

        HttpHeaders httpHeaders = new HttpHeaders();
        headers.forEach(httpHeaders::add);

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(body);
    }
}
