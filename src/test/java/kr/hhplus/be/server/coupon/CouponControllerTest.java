package kr.hhplus.be.server.coupon;

import com.fasterxml.jackson.databind.JsonNode;
import kr.hhplus.be.server.coupon.domain.CouponStatus;
import kr.hhplus.be.server.coupon.dto.GetCouponsResponse;
import kr.hhplus.be.server.coupon.dto.IssueCouponRequest;
import kr.hhplus.be.server.coupon.dto.IssueCouponResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZoneId;

import static kr.hhplus.be.server.MvcMapperUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(CouponController.class)
class CouponControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void 유저의_쿠폰들_조회() throws Exception {
        String userId = "1";

        MockHttpServletResponse response = mvc.perform(
                get("/api/coupon")
                        .queryParam("userId", userId)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());

        GetCouponsResponse couponsResponse = extractResponseDataAsObj(response, GetCouponsResponse.class);
        couponsResponse.coupons().forEach(coupon -> {
            assertEquals(userId, coupon.userId().toString());
            assertEquals(CouponStatus.ISSUED, coupon.couponStatus());
        });

    }

    @Test
    void 쿠폰_발급() throws Exception {
        String userId = "1";

        IssueCouponRequest requestBody = new IssueCouponRequest(Long.parseLong(userId), 1L);

        MockHttpServletResponse response = mvc.perform(
                post("/api/coupon/issue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertBodyAsString(requestBody))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());

        IssueCouponResponse responseCoupon = extractResponseDataAsObj(response, IssueCouponResponse.class);

        assertEquals(userId, responseCoupon.userId().toString());
        assertEquals(CouponStatus.ISSUED, responseCoupon.couponStatus());
    }
}