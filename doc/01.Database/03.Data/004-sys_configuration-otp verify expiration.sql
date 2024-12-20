/**
 * sys_configuration
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: Add register otp expiration
 */
INSERT INTO sys_configuration
(group_code, code, value, public, created_date, created_id)
VALUES('OTP', 'VERIFY_OTP_EXPIRATION', '1800000', false, current_timestamp, 1);

INSERT INTO sys_configuration
(group_code, code, value, public, created_date, created_id)
VALUES('OTP', 'OTP_TIME_INTERVAL', '30000', true, current_timestamp, 1);

