CREATE SCHEMA chat_app AUTHORIZATION postgres;

CREATE TABLE chat_app.users (
    login VARCHAR(64) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(128),
    birth_date DATE,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    role VARCHAR(16) NOT NULL CHECK (role IN ('USER', 'ADMIN'))
);

CREATE TABLE chat_app.messages (
    id SERIAL PRIMARY KEY,
    fromWho VARCHAR(64) NOT NULL,
    toWhom VARCHAR(64) NOT NULL,
    content TEXT NOT NULL,
    sent_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_sender FOREIGN KEY (fromWho) REFERENCES chat_app.users(login) ON DELETE CASCADE,
    CONSTRAINT fk_receiver FOREIGN KEY (toWhom) REFERENCES chat_app.users(login) ON DELETE CASCADE
);

INSERT INTO chat_app.users (
    login,
    password,
    name,
    role
) VALUES (
    'admin',
    'admin1234',
    'Admin',
    'ADMIN'
);

ALTER TABLE IF EXISTS chat_app.users    OWNER TO postgres;
ALTER TABLE IF EXISTS chat_app.messages OWNER TO postgres;
