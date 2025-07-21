-- USER
CREATE TABLE user_point (
    point_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL ,
    point_amount BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- PRODUCT
CREATE TABLE product (
     product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     price BIGINT NOT NULL,
     total_stock BIGINT NOT NULL,
     current_stock BIGINT NOT NULL,
     created_at TIMESTAMP NOT NULL
);

-- ORDER
CREATE TABLE orders (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(32) NOT NULL CHECK (status IN ('CREATED', 'SUCCESS', 'CANCELED'))
);

-- ORDER_ITEM
CREATE TABLE order_item (
    order_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    price BIGINT NOT NULL,
    quantity BIGINT NOT NULL
);

CREATE INDEX idx_order_item_order_id ON order_item(order_id);

-- ORDER_PAYMENT
CREATE TABLE order_payment (
    order_payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    coupon_id BIGINT,
    discount_amount BIGINT NOT NULL,
    total_amount BIGINT NOT NULL
);

-- COUPON_POLICY
CREATE TABLE coupon_policy (
    coupon_policy_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    discount_amount BIGINT NOT NULL,
    total_quantity BIGINT NOT NULL,
    remaining_quantity BIGINT NOT NULL,
    expired_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL
);

-- COUPON
CREATE TABLE coupon (
    coupon_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    coupon_policy_id BIGINT NOT NULL,
    issued_at TIMESTAMP NOT NULL,
    status VARCHAR(32) NOT NULL CHECK (status IN ('ISSUED', 'USED', 'EXPIRED'))
);
