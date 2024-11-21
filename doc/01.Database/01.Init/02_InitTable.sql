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

/**
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: Add column username
 */
drop table if exists auth_account;
create table auth_account (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
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

/**
 * @Date: 2024-11-20
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

/**
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

/**
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: System - Setting Configuration
 */
drop table if exists sys_configuration;
CREATE TABLE sys_configuration (
    id BIGSERIAL PRIMARY KEY,
    group_code VARCHAR(30),
    code VARCHAR(30),
    value VARCHAR(255),
    created_date TIMESTAMP NOT NULL,
    created_id BIGINT,
    updated_date TIMESTAMP,
    updated_id BIGINT,
    deleted_date TIMESTAMP,
    deleted_id BIGINT
);


