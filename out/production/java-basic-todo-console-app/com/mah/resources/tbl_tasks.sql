CREATE TABLE tbl_tasks
(
    id           CHAR(36),
    name         VARCHAR(50) NOT NULL,
    description  VARCHAR(50) NOT NULL,
    status       VARCHAR(50) NOT NULL,
    priority     VARCHAR(50) NOT NULL,
    created_date TIMESTAMP   NOT NULL,
    PRIMARY KEY (id)
);