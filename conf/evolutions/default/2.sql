# --- !Ups

CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    item_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL
);

# --- !Downs

DROP TABLE IF EXISTS orders;
