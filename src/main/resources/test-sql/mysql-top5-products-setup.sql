-- 상품 데이터 삽입
INSERT INTO product (name, price, current_stock, total_stock, created_at, updated_at) VALUES
    ('풍선껌', 1000, 50, 100, NOW(), NOW()),
    ('초콜릿', 1500, 45, 100, NOW(), NOW()),
    ('사탕', 500, 60, 100, NOW(), NOW()),
    ('젤리', 800, 35, 100, NOW(), NOW()),
    ('과자', 2000, 25, 100, NOW(), NOW()),
    ('쿠키', 1200, 40, 100, NOW(), NOW()),
    ('음료수', 3000, 20, 100, NOW(), NOW()),
    ('아이스크림', 2500, 30, 100, NOW(), NOW());

-- 주문 데이터 삽입 (SUCCESS/CANCELED 혼합)
INSERT INTO orders (user_id, created_at, updated_at, status) VALUES
    (1, DATEADD('DAY', -1, NOW()), DATEADD('DAY', -1, NOW()), 'SUCCESS'),
    (2, DATEADD('DAY', -1, NOW()), DATEADD('DAY', -1, NOW()), 'SUCCESS'),
    (3, DATEADD('DAY', -2, NOW()), DATEADD('DAY', -2, NOW()), 'SUCCESS'),
    (4, DATEADD('DAY', -2, NOW()), DATEADD('DAY', -2, NOW()), 'SUCCESS'),
    (5, DATEADD('DAY', -3, NOW()), DATEADD('DAY', -3, NOW()), 'SUCCESS'),
    (6, DATEADD('DAY', -3, NOW()), DATEADD('DAY', -3, NOW()), 'SUCCESS'),
    (7, DATEADD('DAY', -1, NOW()), DATEADD('DAY', -1, NOW()), 'SUCCESS'),
    (8, DATEADD('DAY', -2, NOW()), DATEADD('DAY', -2, NOW()), 'SUCCESS'),
    -- 실패한 주문
    (9, DATEADD('DAY', -1, NOW()), DATEADD('DAY', -1, NOW()), 'CANCELED'),
    (10, DATEADD('DAY', -2, NOW()), DATEADD('DAY', -2, NOW()), 'CANCELED'),
    (11, DATEADD('DAY', -3, NOW()), DATEADD('DAY', -3, NOW()), 'CREATED');

-- 성공한 주문의 아이템들
-- 풍선껌: 총 60개 (1위)
INSERT INTO order_item (order_id, product_id, price, quantity, created_at, updated_at) VALUES
    (1, 1, 1000, 25, NOW(), NOW()),
    (2, 1, 1000, 20, NOW(), NOW()),
    (3, 1, 1000, 15, NOW(), NOW());

-- 초콜릿: 총 55개 (2위)
INSERT INTO order_item (order_id, product_id, price, quantity, created_at, updated_at) VALUES
    (1, 2, 1500, 30, NOW(), NOW()),
    (4, 2, 1500, 25, NOW(), NOW());

-- 사탕: 총 50개 (3위)
INSERT INTO order_item (order_id, product_id, price, quantity, created_at, updated_at) VALUES
    (2, 3, 500, 20, NOW(), NOW()),
    (5, 3, 500, 30, NOW(), NOW());

-- 젤리: 총 45개 (4위)
INSERT INTO order_item (order_id, product_id, price, quantity, created_at, updated_at) VALUES
    (3, 4, 800, 25, NOW(), NOW()),
    (6, 4, 800, 20, NOW(), NOW());

-- 과자: 총 40개 (5위)
INSERT INTO order_item (order_id, product_id, price, quantity, created_at, updated_at) VALUES
    (4, 5, 2000, 15, NOW(), NOW()),
    (7, 5, 2000, 25, NOW(), NOW());

-- 실패한 주문
INSERT INTO order_item (order_id, product_id, price, quantity, created_at, updated_at) VALUES
    (9, 1, 1000, 100, NOW(), NOW()),  -- 풍선껌 대량 주문이지만 CANCELED
    (10, 2, 1500, 200, NOW(), NOW()), -- 초콜릿 대량 주문이지만 CANCELED
    (11, 3, 500, 150, NOW(), NOW());  -- 사탕 대량 주문이지만 CREATED

-- 나머지 상품 (6위 이하)
INSERT INTO order_item (order_id, product_id, price, quantity, created_at, updated_at) VALUES
    (5, 6, 1200, 35, NOW(), NOW()),  -- 쿠키: 35개
    (6, 7, 3000, 30, NOW(), NOW()),  -- 음료수: 30개
    (8, 8, 2500, 25, NOW(), NOW());  -- 아이스크림: 25개