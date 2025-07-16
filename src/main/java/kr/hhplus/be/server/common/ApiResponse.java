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

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("SUCCESS", data, Instant.now());
    }











    public static <T> ResponseEntity<ApiResponse<T>> successWithHeader(T data, Map<String, String> headers) {
        ApiResponse<T> body = new ApiResponse<>("SUCCESS", data, Instant.now());

        HttpHeaders httpHeaders = new HttpHeaders();
        headers.forEach(httpHeaders::add);

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(body);
    }
}
