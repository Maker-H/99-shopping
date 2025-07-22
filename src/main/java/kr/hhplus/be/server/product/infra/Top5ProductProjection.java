package kr.hhplus.be.server.product.infra;

public interface Top5ProductProjection {
    Long getProductId();
    String getName();
    Long getPrice();
    Integer getSalesCount();
    Integer getRank();
}
