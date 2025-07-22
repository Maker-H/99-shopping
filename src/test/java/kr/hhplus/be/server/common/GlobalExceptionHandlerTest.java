package kr.hhplus.be.server.common;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static kr.hhplus.be.server.ApiResponseMapperUtil.extractClientCodeFrom;
import static kr.hhplus.be.server.common.ErrorType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Import(GlobalExceptionHandler.class)
@WebMvcTest(TestExceptionController.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void 커스텀_예외_반환_바디_검증() throws Exception {
        MockHttpServletResponse response = mvc.perform(get("/test/custom-error")).andDo(print()).andReturn().getResponse();

        assertEquals(EXAMPLE_ERROR_TYPE.getHttpStatus().value(), response.getStatus());
        assertEquals(EXAMPLE_ERROR_TYPE.getClientCode(), extractClientCodeFrom(response));
    }

    @Test
    void 알수없는_예외_반환_바디_검증() throws Exception {
        MockHttpServletResponse response = mvc.perform(get("/test/error")).andDo(print()).andReturn().getResponse();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertEquals("UNKNOWN_ERROR", extractClientCodeFrom(response));
    }


}

@RestController
@RequestMapping("/test")
class TestExceptionController {

    @GetMapping("/custom-error")
    public void throwCustomError() {
        throw new CustomException(EXAMPLE_ERROR_TYPE);
    }

    @GetMapping("/error")
    public void throwError() {
        throw new RuntimeException("this is exception");
    }
}