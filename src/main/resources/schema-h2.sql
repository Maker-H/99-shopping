CREATE TABLE user_point (
    point_id IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL,
    point_amount BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE product (
     product_id IDENTITY PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     price BIGINT NOT NULL,
     total_stock BIGINT NOT NULL,
     current_stock BIGINT NOT NULL,
     created_at TIMESTAMP NOT NULL,
     updated_at TIMESTAMP NOT NULL
);

CREATE TABLE orders (
    order_id IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL,
    status VARCHAR(32) NOT NULL CHECK (status IN ('CREATED', 'SUCCESS', 'CANCELED')),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE order_item (
    order_item_id IDENTITY PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    price BIGINT NOT NULL,
    quantity BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_order_item_order_id ON order_item(order_id);

CREATE TABLE order_payment (
   order_payment_id IDENTITY PRIMARY KEY,
   order_id BIGINT NOT NULL,
   coupon_id BIGINT,
   discount_amount BIGINT NOT NULL,
   total_amount BIGINT NOT NULL,
   created_at TIMESTAMP NOT NULL,
   updated_at TIMESTAMP NOT NULL
);

CREATE TABLE coupon_policy (
    coupon_policy_id IDENTITY PRIMARY KEY,
    discount_amount BIGINT NOT NULL,
    total_quantity BIGINT NOT NULL,
    remaining_quantity BIGINT NOT NULL,
    expired_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE coupon (
    coupon_id IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL,
    coupon_policy_id BIGINT NOT NULL,
    issued_at TIMESTAMP NOT NULL,
    status VARCHAR(32) NOT NULL CHECK (status IN ('ISSUED', 'USED', 'EXPIRED')),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);