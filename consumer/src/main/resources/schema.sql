CREATE TABLE IF NOT EXISTS PERSONS
(
    PERSON_ID       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    PERSON_NAME     VARCHAR(20) NOT NULL,
    FAMILY          VARCHAR(50) NOT NULL,
    VOTES           BIGINT NULL,
    CREATED         TIMESTAMP NOT NULL,

    CONSTRAINT PERSON_PK PRIMARY KEY (PERSON_ID),
    CONSTRAINT FAMILY_U UNIQUE (FAMILY)
);