package kr.hhplus.be.server.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorType {
    EXAMPLE_ERROR_TYPE(HttpStatus.BAD_REQUEST, "CLIENT_CODE", "this is description for server log"),
    ;

    private final HttpStatus httpStatus;
    private final String clientCode;
    private final String errorDescription;
}
