CREATE TABLE IF NOT EXISTS books (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(50),
    author VARCHAR(50),
    category VARCHAR(50),
    available BOOLEAN
);