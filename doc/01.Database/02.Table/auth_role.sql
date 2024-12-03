/**
 * auth_role
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: Authentication - Roles
 */
drop table if exists auth_role;
CREATE TABLE auth_role (
    id BIGSERIAL PRIMARY KEY,
    "name" VARCHAR(20),
    created_date TIMESTAMP NOT NULL,
    created_id BIGINT,
    updated_date TIMESTAMP,
    updated_id BIGINT,
    deleted_date TIMESTAMP,
    deleted_id BIGINT
);