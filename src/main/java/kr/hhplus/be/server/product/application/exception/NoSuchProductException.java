package kr.hhplus.be.server.product.application.exception;

import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.ErrorType;

public class NoSuchProductException extends CustomException {

    public NoSuchProductException(String detailMessage) {
        super(ErrorType.NO_SUCH_PRODUCT, detailMessage);
    }
}
