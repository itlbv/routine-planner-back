DROP TABLE IF EXISTS routines;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name          VARCHAR   NOT NULL,
    last_name     VARCHAR,
    email         VARCHAR   NOT NULL,
    reg_date_time TIMESTAMP NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE routines
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id     INTEGER NOT NULL,
    name        VARCHAR NOT NULL,
    description VARCHAR,
    start_date  DATE    NOT NULL,
    end_date    DATE,
    time_of_day TIME    NOT NULL,
    period      INTERVAL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
