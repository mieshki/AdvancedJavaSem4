ALTER TABLE users
    ADD COLUMN role CHAR(15) NOT NULL DEFAULT 'user';

CREATE TABLE department
(
    id   BIGINT      NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE category
(
    id            BIGINT      NOT NULL,
    name          VARCHAR(50) NOT NULL,
    department_id BIGINT      NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (department_id) REFERENCES department (id)
);

CREATE TABLE auction
(
    id          BIGINT         NOT NULL,
    title       VARCHAR(50)    NOT NULL,
    description VARCHAR(500)   NOT NULL,
    price       DECIMAL(15, 2) NOT NULL,
    category_id BIGINT         NOT NULL,
    owner_id    BIGINT    	   NOT NULL,
    version     BIGINT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES category (id),
    FOREIGN KEY (owner_id) REFERENCES users (id)
);

CREATE TABLE photo
(
    id         BIGINT       NOT NULL,
    url        VARCHAR(500) NOT NULL,
    auction_id BIGINT       NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (auction_id) REFERENCES auction (id)
);

CREATE TABLE parameter
(
    id   BIGINT      NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE auction_parameter
(
    auction_parameter_id BIGINT       NOT NULL,
    auction_id           BIGINT       NOT NULL,
    parameter_id         BIGINT       NOT NULL,
    value                VARCHAR(100) NOT NULL,
    FOREIGN KEY (auction_id) REFERENCES auction (id),
    FOREIGN KEY (parameter_id) REFERENCES parameter (id)
);