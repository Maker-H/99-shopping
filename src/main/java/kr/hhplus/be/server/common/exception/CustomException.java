package kr.hhplus.be.server.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorType errorType;

    private final String customMessage;

    public CustomException(ErrorType errorType) {
        super(errorType.getErrorDescription());
        this.errorType = errorType;
        this.customMessage = errorType.getErrorDescription();
    }

    public CustomException(ErrorType errorType, String customMessage) {
        super(errorType.getClientCode());
        this.errorType = errorType;
        this.customMessage = customMessage;
    }

    public String getClientCode() {
        return errorType.getClientCode();
    }

    public HttpStatus getHttpStatus() {
        return errorType.getHttpStatus();
    }

}
