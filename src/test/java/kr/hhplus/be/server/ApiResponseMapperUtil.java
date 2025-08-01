package kr.hhplus.be.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.mock.web.MockHttpServletResponse;

public class ApiResponseMapperUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static <T> String convertAsString(final T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractResponseDataAsString(final MockHttpServletResponse servletResponse) {
        try {
            JsonNode root = objectMapper.readTree(servletResponse.getContentAsString());
            return objectMapper.writeValueAsString(root.get("data"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T extractResponseDataAsObj(final MockHttpServletResponse servletResponse, Class<T> clazz) {
        try {
            JsonNode root = objectMapper.readTree(servletResponse.getContentAsString());
            JsonNode dataNode = root.get("data");
            return objectMapper.treeToValue(dataNode, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T extractResponseDataAsObj(final MockHttpServletResponse servletResponse, TypeReference<T> typeReference) {
        try {
            JsonNode root = objectMapper.readTree(servletResponse.getContentAsString());
            JsonNode dataNode = root.get("data");
            return objectMapper.treeToValue(dataNode, typeReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractClientCodeFrom(final MockHttpServletResponse servletResponse) {
        try {
            JsonNode root = objectMapper.readTree(servletResponse.getContentAsString());
            return root.get("clientCode").asText();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}