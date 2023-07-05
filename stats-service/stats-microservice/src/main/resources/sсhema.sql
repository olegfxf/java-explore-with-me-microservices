DROP TABLE IF EXISTS hits CASCADE;

CREATE TABLE IF NOT EXISTS hits
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    app       VARCHAR(100)                                        NOT NULL,
    uri       VARCHAR(100),
    ip        VARCHAR(16),
    timestamp TIMESTAMP
);
