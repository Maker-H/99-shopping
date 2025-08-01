package kr.hhplus.be.server.point.application.exception;

import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.ErrorType;

public class InsufficientPointException extends CustomException {
    public InsufficientPointException(String customMessage) {
        super(ErrorType.INSUFFICIENT_POINT, customMessage);
    }
}
