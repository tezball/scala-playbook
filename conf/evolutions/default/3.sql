# --- !Ups

CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(50) NOT NULL,
    base_price DOUBLE PRECISION NOT NULL,
    discount_type VARCHAR(50) NOT NULL,
    discount_value DOUBLE PRECISION NOT NULL,
    final_price DOUBLE PRECISION NOT NULL
);

# --- !Downs

DROP TABLE IF EXISTS products;
