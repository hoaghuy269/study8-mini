/**
 * sys_otp
 * @Date: 2024-11-26
 * @Author: HuyNH
 * @Desc: sys_otp
 */
drop table if exists sys_otp;
CREATE TABLE sys_otp (
    id BIGSERIAL PRIMARY KEY,
    "type" varchar(30) NOT NULL,
    user_id BIGINT NOT NULL,
    code VARCHAR(50),
    active BOOLEAN,
    sent_date TIMESTAMP,
    expiry_date TIMESTAMP NOT NULL,
    verified BOOLEAN,
    verified_date TIMESTAMP,
    created_date TIMESTAMP NOT NULL,
    created_id BIGINT,
    updated_date TIMESTAMP,
    updated_id BIGINT,
    deleted_date TIMESTAMP,
    deleted_id BIGINT

);