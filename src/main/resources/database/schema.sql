CREATE TABLE IF NOT EXISTS reference_book
(
    id            BIGINT AUTO_INCREMENT,
    mnemonic      VARCHAR(10)  NOT NULL,
    currency_code INT(10)      NOT NULL,
    description   VARCHAR(250) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS journal
(
    id            BIGINT AUTO_INCREMENT,
    current       DATE    NOT NULL,
    currency_code INT(10) NOT NULL,
    buy           DOUBLE  NOT NULL,
    sale          DOUBLE  NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS usr
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    login    VARCHAR(50)  NOT NULL,
    password VARCHAR(250) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id BIGINT      NOT NULL,
    roles   VARCHAR(10) NOT NULL,
    UNIQUE (user_id, roles)
);


