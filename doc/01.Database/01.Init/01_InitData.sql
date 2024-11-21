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

