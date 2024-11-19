/**
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: Init table auth_account
 */
drop table if exists auth_account;
create table auth_account (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(255) NOT NULL,
    "password" VARCHAR(255),
    status VARCHAR(10) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    created_id BIGINT,
    updated_date TIMESTAMP,
    updated_id BIGINT,
    deleted_date TIMESTAMP,
    deleted_id BIGINT
);