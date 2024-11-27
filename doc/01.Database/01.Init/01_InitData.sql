/**
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: Add data roles
 */
delete from auth_role;
INSERT INTO auth_role
("name", created_date, created_id)
VALUES('ROLE_ADMIN', current_timestamp, 1);
INSERT INTO auth_role
("name", created_date, created_id)
VALUES('ROLE_STUDENT', current_timestamp, 1);
INSERT INTO auth_role
("name", created_date, created_id)
VALUES('ROLE_TEACHER', current_timestamp, 1);
INSERT INTO auth_role
("name", created_date, created_id)
VALUES('ROLE_VISITOR', current_timestamp, 1);

/**
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: Add jwt secret
 */
delete from sys_configuration WHERE code = 'JWT_SECRET';
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id)
VALUES('JWT', 'JWT_SECRET', '***', current_timestamp, 1);

/**
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: Add jwt ex
 */
delete from sys_configuration WHERE code = 'JWT_EXPIRATION';
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id)
VALUES('JWT', 'JWT_EXPIRATION', '1800000', current_timestamp, 1);

/**
 * sys_configuration
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: Add email 
 */
delete from sys_configuration WHERE code = 'EMAIL_USERNAME';
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id)
VALUES('EMAIL', 'EMAIL_USERNAME', 'study8.it@gmail.com', current_timestamp, 1);

delete from sys_configuration WHERE code = 'EMAIL_PASSWORD';
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id)
VALUES('EMAIL', 'EMAIL_PASSWORD', '***', current_timestamp, 1);

/**
 * sys_configuration
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: Add pagination config
 */
delete from sys_configuration WHERE code = 'PAGE';
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id)
VALUES('PAGINATION', 'PAGE', '0', current_timestamp, 1);

delete from sys_configuration WHERE code = 'PAGE_SIZE';
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id)
VALUES('PAGINATION', 'PAGE_SIZE', '50', current_timestamp, 1);



