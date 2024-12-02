/**
 * sys_configuration
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: Add pagination config
 */
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id)
VALUES('PAGINATION', 'PAGE', '0', current_timestamp, 1);

INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id)
VALUES('PAGINATION', 'PAGE_SIZE', '50', current_timestamp, 1);
