CREATE TABLE USER
(
    ID         BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME       VARCHAR(100),
    TEL        VARCHAR(20) UNIQUE,
    AVATAR_URL VARCHAR(1024),
    ADDRESS    VARCHAR(1024),
    CREATED_AT TIMESTAMP NOT NULL DEFAULT NOW(),
    UPDATED_AT TIMESTAMP NOT NULL DEFAULT NOW()
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

INSERT INTO USER(ID, NAME, TEL, AVATAR_URL, ADDRESS)
VALUES (1, 'user1', '13800000000', 'http://url', '火星')