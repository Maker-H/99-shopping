package kr.hhplus.be.server.point;

import kr.hhplus.be.server.point.dto.ChargePointRequest;
import kr.hhplus.be.server.point.dto.ChargePointResponse;
import kr.hhplus.be.server.point.dto.GetPointResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static kr.hhplus.be.server.MvcMapperUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(PointController.class)
class PointControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void 포인트_충전() throws Exception {
        long userId = 1L;
        long amount = 100L;
        ChargePointRequest pointRequest = new ChargePointRequest(userId, amount);

        MockHttpServletResponse response = mvc.perform(
                patch("/api/point/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertAsString(pointRequest))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());

        ChargePointResponse actual = extractResponseDataAsObj(response, ChargePointResponse.class);
        ChargePointResponse expected = new ChargePointResponse(userId, amount);
        assertEquals(expected, actual);
    }

    @Test
    void 포인트_조회() throws Exception {
        String userId = "1";

        MockHttpServletResponse response = mvc.perform(
                get("/api/point").queryParam("userId", userId)
        ).andReturn().getResponse();

        GetPointResponse getPointResponse = extractResponseDataAsObj(response, GetPointResponse.class);
        assertEquals(200, response.getStatus());
        assertEquals(userId, getPointResponse.userId().toString());
    }

}