package kr.hhplus.be.server.product.application.exception;

import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.ErrorType;

public class NoMatchingProductException extends CustomException {

    public NoMatchingProductException(ErrorType errorType, String detailMessage) {
        super(errorType, detailMessage);
    }
}
