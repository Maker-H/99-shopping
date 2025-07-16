package kr.hhplus.be.server.order;

import kr.hhplus.be.server.MvcMapperUtil;
import kr.hhplus.be.server.order.domain.OrderStatus;
import kr.hhplus.be.server.order.dto.CreateOrderRequest;
import kr.hhplus.be.server.order.dto.CreateOrderResponse;
import kr.hhplus.be.server.order.dto.OrderItemDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static kr.hhplus.be.server.MvcMapperUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void 주문을_생성한다() throws Exception {
        String userId = "1";
        String txKey = "AAA";

        CreateOrderRequest requestBody = new CreateOrderRequest(
                Long.parseLong(userId),
                1L,
                List.of(new OrderItemDto(1L, 1L))
        );

        MvcResult result = mvc.perform(
                post("/api/order")
                        .header("txKey", txKey)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertAsString(requestBody))
        ).andReturn();

        Assertions.assertEquals(txKey, result.getRequest().getHeader("txKey"));

        Assertions.assertEquals(200, result.getResponse().getStatus());
        Assertions.assertEquals(txKey, result.getResponse().getHeader("txKey"));

        CreateOrderResponse responseData = extractResponseDataAsObj(result.getResponse(), CreateOrderResponse.class);
        Assertions.assertEquals(Long.parseLong(userId), responseData.userId());
        Assertions.assertEquals(OrderStatus.SUCCESS, responseData.orderStatus());
    }

}