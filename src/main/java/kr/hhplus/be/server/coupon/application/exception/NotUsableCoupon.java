package kr.hhplus.be.server.coupon.application.exception;

import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.ErrorType;

public class NotUsableCoupon extends CustomException {

    public NotUsableCoupon(String customMessage) {
        super(ErrorType.NOT_USABLE_COUPON, customMessage);
    }
}
