/**
 * sys_configuration
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: Add email 
 */
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id)
VALUES('EMAIL', 'EMAIL_USERNAME', 'study8.it@gmail.com', current_timestamp, 1);

INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id)
VALUES('EMAIL', 'EMAIL_PASSWORD', '***', current_timestamp, 1);
