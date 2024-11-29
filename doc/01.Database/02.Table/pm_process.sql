/**
 * pm_process
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: pm_process
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
