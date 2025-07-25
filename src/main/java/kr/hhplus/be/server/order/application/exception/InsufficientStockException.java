package kr.hhplus.be.server.order.application.exception;

import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.ErrorType;

public class InsufficientStockException extends CustomException {

    public InsufficientStockException(String customMessage) {
        super(ErrorType.INSUFFICIENT_STOCK, customMessage);
    }
}
