package kr.hhplus.be.server.product.web;

import com.fasterxml.jackson.core.type.TypeReference;
import kr.hhplus.be.server.E2ETest;
import kr.hhplus.be.server.common.exception.ErrorType;
import kr.hhplus.be.server.product.application.dto.GetTop5ProductResponse;
import kr.hhplus.be.server.product.application.mapper.ProductMapper;
import kr.hhplus.be.server.product.domain.ProductDetail;
import kr.hhplus.be.server.product.domain.ProductEntity;
import kr.hhplus.be.server.product.application.dto.GetProductResponse;
import kr.hhplus.be.server.product.infra.ProductJpaRepository;
import org.hibernate.cfg.Environment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kr.hhplus.be.server.ApiResponseMapperUtil.extractClientCodeFrom;
import static kr.hhplus.be.server.ApiResponseMapperUtil.extractResponseDataAsObj;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class ProductControllerTest extends E2ETest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductJpaRepository repository;

    @Test
    void 존재하는_상품ID로_조회() throws Exception {

        //setup
        ProductEntity savedEntity = repository.save( new ProductEntity(new ProductDetail("풍선껌", 1000L, 10L, 20L)));
        Long savedProductId = savedEntity.getId();

        //start
        MockHttpServletResponse response = mvc.perform(
                get("/api/product/" + savedProductId)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());

        GetProductResponse actual = extractResponseDataAsObj(response, GetProductResponse.class);
        GetProductResponse expected = ProductMapper.toGetProductResponse(savedEntity);
        assertEquals(expected, actual);
    }

    @Test
    void 존재하지_않는_상품ID로_예외() throws Exception {
        long unknownId = 99999999L;

        MockHttpServletResponse response = mvc.perform(
                get("/api/product/" + unknownId)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
        assertEquals(ErrorType.NO_SUCH_PRODUCT.getHttpStatus().value(), response.getStatus());

        String actualClientCode = extractClientCodeFrom(response);
        assertEquals(ErrorType.NO_SUCH_PRODUCT.getClientCode(), actualClientCode);
    }

    @Test
    @EnabledIf("isMysqlEnabled")
    @Sql("/test-sql/mysql-top5-products-setup.sql")
    void 최근_3일_판매량_상위_5개_상품() throws Exception {

        MockHttpServletResponse response = mvc.perform(
                get("/api/product/top5")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());

        List<GetTop5ProductResponse> actual = extractResponseDataAsObj(response, new TypeReference<>(){});

        // 최대 5개까지 반환되는지 검증
        assertEquals(5, actual.size());

        // 판매량 순 정렬 검증
        for (int i = 0; i < actual.size() - 1; i++) {
            assertTrue(actual.get(i).salesCount() >= actual.get(i + 1).salesCount());
        }

        // 순위 검증
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(i + 1, actual.get(i).rank());
        }
    }

}
