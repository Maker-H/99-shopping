package kr.hhplus.be.server.common;

import java.time.Instant;

public record ApiResponse<T> (
        String clientCode,
        T data,
        Instant timeStamp
){

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("SUCCESS", data, Instant.now());
    }

}
