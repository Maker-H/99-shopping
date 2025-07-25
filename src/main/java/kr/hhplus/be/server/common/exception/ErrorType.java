package kr.hhplus.be.server.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ErrorType {
    EXAMPLE_ERROR_TYPE(HttpStatus.BAD_REQUEST, "CLIENT_CODE", "this is description for server log"),
    NO_SUCH_PRODUCT(HttpStatus.BAD_REQUEST, "NO_SUCH_PRODUCT", "there is no such product"),
    INSUFFICIENT_POINT(HttpStatus.BAD_REQUEST, "INSUFFICIENT_POINT", "point is insufficient"),
    NO_SUCH_COUPON(HttpStatus.BAD_REQUEST, "NO_SUCH_COUPON", "there is no such coupon" ),
    NOT_USABLE_COUPON(HttpStatus.BAD_REQUEST, "NOT_USABLE_COUPON", "coupon is not usable" ),
    INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST, "INSUFFICIENT_STOCK" , "stock is insufficient" ),


    ;

    @Getter(AccessLevel.PUBLIC)
    private final HttpStatus httpStatus;

    @Getter(AccessLevel.PUBLIC)
    private final String clientCode;

    @Getter(AccessLevel.PACKAGE)
    private final String errorDescription;
}
