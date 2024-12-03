/**
 * sys_configuration
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: Camunda configuration
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


