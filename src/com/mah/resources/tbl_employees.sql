CREATE TABLE tbl_employees
(
    id         CHAR(36),
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50),
    salary     INT,
    PRIMARY KEY (id)
);