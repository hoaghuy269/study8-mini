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

/**
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: Remove username not null
 */
drop table if exists auth_account;
create table auth_account (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
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
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: pm_process
 */
drop table if exists pm_process;
create table pm_process (
    id BIGSERIAL PRIMARY KEY,
    business_id BIGINT,
    process_id VARCHAR(30),
    created_date TIMESTAMP NOT NULL,
    created_id BIGINT,
    updated_date TIMESTAMP,
    updated_id BIGINT,
    deleted_date TIMESTAMP,
    deleted_id BIGINT
);

/**
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: process_id to VARCHAR(50)
 */
drop table if exists pm_process;
create table pm_process (
    id BIGSERIAL PRIMARY KEY,
    business_id BIGINT,
    process_id VARCHAR(50),
    created_date TIMESTAMP NOT NULL,
    created_id BIGINT,
    updated_date TIMESTAMP,
    updated_id BIGINT,
    deleted_date TIMESTAMP,
    deleted_id BIGINT
);

/**
 * @Date: 2024-11-26
 * @Author: HuyNH
 * @Desc: Create table sys_otp
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

/**
 * auth_account
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: Remove name not null
 */
drop table if exists auth_account;
create table auth_account (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    code VARCHAR(50) UNIQUE NOT NULL,
    "name" VARCHAR(255),
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
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: pm_process - add code
 */
drop table if exists pm_process;
create table pm_process (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(20),
    business_id BIGINT not null,
    process_id VARCHAR(50) not null,
    created_date TIMESTAMP NOT NULL,
    created_id BIGINT,
    updated_date TIMESTAMP,
    updated_id BIGINT,
    deleted_date TIMESTAMP,
    deleted_id BIGINT
);





