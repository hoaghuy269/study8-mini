/**
 * sys_configuration
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: Add register otp expiration
 */
INSERT INTO sys_configuration
(group_code, code, value, created_date, created_id)
VALUES('OTP', 'VERIFY_OTP_EXPIRATION', '600000 ', current_timestamp, 1);
