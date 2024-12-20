/**
 * sys_config
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
    public_flag boolean,
    created_date TIMESTAMP NOT NULL,
    created_id BIGINT,
    updated_date TIMESTAMP,
    updated_id BIGINT,
    deleted_date TIMESTAMP,
    deleted_id BIGINT
);
