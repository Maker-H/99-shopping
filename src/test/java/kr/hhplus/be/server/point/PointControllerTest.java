package kr.hhplus.be.server.point;

import kr.hhplus.be.server.E2ETest;
import kr.hhplus.be.server.point.domain.Amount;
import kr.hhplus.be.server.user.domain.UserId;
import kr.hhplus.be.server.point.domain.UserPointEntity;
import kr.hhplus.be.server.point.application.dto.ChargePointRequest;
import kr.hhplus.be.server.point.application.dto.ChargePointResponse;
import kr.hhplus.be.server.point.application.dto.GetPointResponse;
import kr.hhplus.be.server.point.infra.PointJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.ThreadLocalRandom;

import static kr.hhplus.be.server.ApiResponseMapperUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


class PointControllerTest extends E2ETest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PointJpaRepository repository;

    private Long userIdValue;
    private Long initAmountValue = 100L;

    @BeforeEach
    public void 테스트_전_테이블에_포인트가_100인_유저를_삽입한다() {
        long randomId = ThreadLocalRandom.current().nextLong(1_000L, 9_999_999L);
        userIdValue = randomId;

        UserPointEntity userPointEntity = UserPointEntity.empty(new UserId(userIdValue));
        userPointEntity.charge(new Amount(initAmountValue));
        repository.save(userPointEntity);
    }

    @Test
    void 포인트_정보가_없는_유저이면_정보를_생성하고_충전한다() throws Exception {

        Long newUserIdValue = 1L;
        Long addedAmount = 1000L;

        ChargePointRequest request = new ChargePointRequest(newUserIdValue, addedAmount);

        MockHttpServletResponse response = mvc.perform(
                patch("/api/point/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertAsString(request))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());

        ChargePointResponse actual = extractResponseDataAsObj(response, ChargePointResponse.class);
        ChargePointResponse expected = new ChargePointResponse(newUserIdValue, addedAmount);
        assertEquals(expected, actual);
    }

    @Test
    void 포인트_충전() throws Exception {

        Long addedAmount = 1000L;
        Long resultAmount = initAmountValue + addedAmount;

        ChargePointRequest request = new ChargePointRequest(userIdValue, addedAmount);

        MockHttpServletResponse response = mvc.perform(
                patch("/api/point/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertAsString(request))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());

        ChargePointResponse actual = extractResponseDataAsObj(response, ChargePointResponse.class);
        ChargePointResponse expected = new ChargePointResponse(userIdValue, resultAmount);
        assertEquals(expected, actual);

    }

    @Test
    void 포인트_충전금액이_0이하이면_예외() throws Exception {

        Long addedAmount = -100L;

        ChargePointRequest request = new ChargePointRequest(userIdValue, addedAmount);

        MockHttpServletResponse response = mvc.perform(
                patch("/api/point/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertAsString(request))
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void 포인트_조회() throws Exception {

        MockHttpServletResponse response = mvc.perform(
                get("/api/point").queryParam("userId", userIdValue.toString())
        ).andReturn().getResponse();

        GetPointResponse actual = extractResponseDataAsObj(response, GetPointResponse.class);
        GetPointResponse expected = new GetPointResponse(userIdValue, initAmountValue);

        assertEquals(200, response.getStatus());
        assertEquals(expected, actual);

    }

    @Test
    void 포인트_정보가_없는_유저이면_0으로_초기화_되어_저장되고_반환된다() throws Exception {

        Long newUserValue = 1L;

        MockHttpServletResponse response = mvc.perform(
                get("/api/point").queryParam("userId", newUserValue.toString())
        ).andReturn().getResponse();

        GetPointResponse actual = extractResponseDataAsObj(response, GetPointResponse.class);
        GetPointResponse expected = new GetPointResponse(newUserValue, 0L);

        assertEquals(200, response.getStatus());
        assertEquals(expected, actual);
    }

}