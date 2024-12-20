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

/**
 * sys_configuration
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: Init otp config
 */
delete from sys_configuration where group_code = 'OTP';
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id)
VALUES('OTP', 'VERIFY_OTP_EXPIRATION', '1800000', current_timestamp, 1);

/**
 * sys_configuration
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: Init Camunda Config
 */
delete from sys_configuration where group_code = 'CAMUNDA';

INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id)
VALUES('CAMUNDA', 'CAMUNDA_ENABLE', '1', current_timestamp, 1);

INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id)
VALUES('CAMUNDA', 'CAMUNDA_URL', 'http://localhost:8090/api/v1/process', current_timestamp, 1);

INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id)
VALUES('CAMUNDA', 'CAMUNDA_START_PROCESS_URL', '/start', current_timestamp, 1);

INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id)
VALUES('CAMUNDA', 'CAMUNDA_COMPLETE_TASK_URL', '/complete-task', current_timestamp, 1);

/**
 * sys_configuration
 * @Date: 2024-12-17
 * @Author: HuyNH
 * @Desc: Init all config
 */
delete from sys_configuration;
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id, public_flag)
VALUES('JWT', 'JWT_EXPIRATION', '1800000', '2024-11-29 09:46:50.555', 1, false);
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id, public_flag)
VALUES('EMAIL', 'EMAIL_USERNAME', 'study8.it@gmail.com', '2024-11-29 09:46:50.555', 1, false);
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id, public_flag)
VALUES('PAGINATION', 'PAGE', '0', '2024-11-29 09:46:50.555', 1, false);
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id, public_flag)
VALUES('PAGINATION', 'PAGE_SIZE', '50', '2024-11-29 09:46:50.555', 1, false);
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id, public_flag)
VALUES('CAMUNDA', 'CAMUNDA_URL', 'http://localhost:8090/api/v1/process', '2024-12-02 09:18:31.809', 1, false);
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id, public_flag)
VALUES('CAMUNDA', 'CAMUNDA_START_PROCESS_URL', '/start', '2024-12-02 09:18:31.809', 1, false);
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id, public_flag)
VALUES('EMAIL', 'EMAIL_PASSWORD', '***', '2024-11-29 09:46:50.555', 1, false);
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id, public_flag)
VALUES('JWT', 'JWT_SECRET', '***', '2024-11-29 09:46:50.555', 1, false);
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id, public_flag)
VALUES('CAMUNDA', 'CAMUNDA_COMPLETE_TASK_URL', '/complete-task', '2024-12-03 03:06:32.832', 1, false);
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id, public_flag)
VALUES('OTP', 'VERIFY_OTP_EXPIRATION', '1800000', '2024-12-03 03:30:30.339', 1, false);
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id, public_flag)
VALUES('CAMUNDA', 'CAMUNDA_ENABLE', '0', '2024-12-02 09:18:31.809', 1, false);

/**
 * sys_configuration
 * @Date: 2024-12-17
 * @Author: HuyNH
 * @Desc: OTP config
 */
delete from sys_configuration where group_code = 'OTP';

INSERT INTO sys_configuration
(group_code, code, value, public_flag, created_date, created_id)
VALUES('OTP', 'VERIFY_OTP_EXPIRATION', '1800000', false, current_timestamp, 1);

INSERT INTO sys_configuration
(group_code, code, value, public_flag, created_date, created_id)
VALUES('OTP', 'OTP_TIME_INTERVAL', '30000', true, current_timestamp, 1);

/**
 * sys_configuration
 * @Date: 2024-12-18
 * @Author: HuyNH
 * @Desc: JWT forgot password config
 */
INSERT INTO sys_configuration
(group_code, code, value, public_flag, created_date, created_id, updated_date, updated_id, deleted_date, deleted_id)
VALUES('JWT', 'JWT_FP_EXPIRATION', '1800000', false, '2024-11-29 09:46:50.555', 1, NULL, NULL, NULL, NULL);

INSERT INTO sys_configuration
(group_code, code, value, public_flag, created_date, created_id, updated_date, updated_id, deleted_date, deleted_id)
VALUES('JWT', 'JWT_FP_SECRET', '***', false, '2024-11-29 09:46:50.555', 1, NULL, NULL, NULL, NULL);






