DROP TABLE reference_book;
DROP TABLE journal;


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



