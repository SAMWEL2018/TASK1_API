CREATE DATABASE TASK1;
USE TASK1;

CREATE TABLE TASK1.tbl_accounts
(
    id        varchar(50) NOT NULL,
    balance   float8      NOT NULL,
    credit    float8      NOT NULL,
    debit     float8      NOT NULL,
    id_number varchar(50) NOT NULL,
    "name"    varchar(50) NOT NULL,
    CONSTRAINT tbl_accounts_pkey PRIMARY KEY (id)
);
CREATE TABLE TASK1.tbl_transactions
(
    transactionid       varchar(50)  NOT NULL,
    amount              varchar(50)  NOT NULL,
    receiver_account_no varchar(20)  NULL,
    sender_account_no   varchar(20)  NOT NULL,
    transaction_time    timestamp(6) NULL,
    transactiontype     varchar(50)  NOT NULL,
    CONSTRAINT tbl_transactions_pkey PRIMARY KEY (transactionid)
);