/**
 * auth_role
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
