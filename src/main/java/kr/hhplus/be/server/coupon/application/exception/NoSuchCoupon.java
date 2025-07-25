package kr.hhplus.be.server.coupon.application.exception;

import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.ErrorType;
import org.jetbrains.annotations.NotNull;

public class NoSuchCoupon extends CustomException {

    public NoSuchCoupon(String customMessage) {
        super(ErrorType.NO_SUCH_COUPON, customMessage);
    }
}
