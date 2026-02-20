# --- !Ups

CREATE TABLE reviews (
    id BIGSERIAL PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    rating INT NOT NULL CHECK (rating >= 1 AND rating <= 5),
    comment TEXT NOT NULL,
    created_at BIGINT NOT NULL
);

# --- !Downs

DROP TABLE IF EXISTS reviews;
