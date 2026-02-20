# --- !Ups

CREATE TABLE ledger_entries (
    id BIGSERIAL PRIMARY KEY,
    account_name VARCHAR(255) NOT NULL,
    tx_type VARCHAR(50) NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    description TEXT NOT NULL,
    created_at BIGINT NOT NULL
);

# --- !Downs

DROP TABLE IF EXISTS ledger_entries;
