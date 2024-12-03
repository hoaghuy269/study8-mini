/**
 * sys_configuration
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: Add jwt ex
 */
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id)
VALUES('JWT', 'JWT_EXPIRATION', '1800000', current_timestamp, 1);
