/**
 * auth_account_role
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: auth_account - auth_role reference 
 */
drop table if exists auth_account_role;
create table auth_account_role (
	id BIGSERIAL PRIMARY KEY,
    account_id BIGINT,
    role_id BIGINT,
    created_date TIMESTAMP NOT NULL,
    created_id BIGINT,
    updated_date TIMESTAMP,
    updated_id BIGINT,
    deleted_date TIMESTAMP,
    deleted_id BIGINT
);