# --- !Ups

CREATE TABLE coupons (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    discount_percent DOUBLE PRECISION NOT NULL,
    expires_at VARCHAR(10) NOT NULL,
    uses_remaining INT NOT NULL,
    minimum_order DOUBLE PRECISION NOT NULL
);

INSERT INTO coupons (code, discount_percent, expires_at, uses_remaining, minimum_order) VALUES
    ('SAVE10', 10.0, '2027-12-31', 100, 0.0),
    ('EXPIRED22', 22.0, '2023-01-01', 50, 0.0),
    ('USED_UP', 15.0, '2027-12-31', 0, 0.0),
    ('BIGSPEND', 25.0, '2027-12-31', 10, 500.0),
    ('WELCOME', 20.0, '2027-06-30', 1000, 25.0);

# --- !Downs

DROP TABLE IF EXISTS coupons;
