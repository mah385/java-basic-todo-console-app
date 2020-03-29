CREATE TABLE tbl_users
(
    id         CHAR(36),
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50),
    email      VARCHAR(50) NOT NULL UNIQUE,
    username   VARCHAR(50) NOT NULL UNIQUE,
    password   VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);