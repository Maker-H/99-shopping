package kr.hhplus.be.server.common;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private final ErrorType errorType;

    public CustomException(ErrorType errorType) {
        super(errorType.getErrorDescription());
        this.errorType = errorType;
    }

    public String getClientCode() {
        return errorType.getClientCode();
    }

    public HttpStatus getHttpStatus() {
        return errorType.getHttpStatus();
    }
}
