create table USER
(
    ID         INT auto_increment primary key not null ,
    ACCOUNT_ID VARCHAR(100),
    NAME       VARCHAR(50),
    TOKEN      CHAR(36),
    GMT_CREATE BIGINT,
    GMT_UPDATE BIGINT
);