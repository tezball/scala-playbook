# --- !Ups

CREATE TABLE workflow_orders (
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'Pending',
    status_data TEXT NOT NULL DEFAULT '',
    created_at BIGINT NOT NULL,
    updated_at BIGINT NOT NULL
);

# --- !Downs

DROP TABLE IF EXISTS workflow_orders;
