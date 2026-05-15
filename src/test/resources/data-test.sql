-- 1. roles
INSERT INTO role (role_type, role_rate)
VALUES ('Project Manager', 2500),
       ('Subproject Manager', 1500),
       ('Program Manager', 1200);

-- 2. employee
INSERT INTO employee (employee_name, employee_username, employee_password, role_id)
VALUES ('Joakim', 'joakim_dev', 'password_123', 1),
       ('Emil', 'emil_dev', 'password_456', 2),
       ('Simon', 'simon_dev', 'password_678', 3),
       ('Jackie', 'jackie_dev', 'password_777', 3);

-- 3. projects
INSERT INTO project (project_name, start_date, deadline, project_manager)
VALUES ('Alpha Solutions', '2026-01-01', '2026-12-31', 1),
       ('AI Model', '2027-01-01', '2028-01-01', 2);

-- 4. subprojects
INSERT INTO subproject (sub_name, start_date, deadline, completed, project_id)
VALUES ('Project calculation tool', '2026-02-01', '2026-03-01', false, 1),
       ('AI integration', '2026-06-06', '2026-12-12', false, 2);

-- 5. complexity
INSERT INTO complexity (complexity_score, label_type, rate_multiplier)
VALUES (1, 'Standard', 1.0),
       (5, 'Elevated', 1.5),
       (10, 'Enterprise Grade', 3.0);

-- 6. project_employee
INSERT INTO project_employee (employee_id, project_id)
VALUES (1,1), -- Joakim
       (2,1), -- Emil
       (3,1); -- Simon

-- 7. sub_project_employee
INSERT INTO sub_project_employee (project_employee_id, sub_id)
VALUES (1,1), -- Joakim
       (2,1); -- Emil

INSERT INTO task (start_date, completed, task_name, deadline, task_time, task_price, project_id, subproject_id, employee_id, current_complexity_id)
VALUES ('2027-01-01', false, 'Alpha Task One', '2027-12-01', 10, 100, 1, 1, 1, 1),
       ('2027-02-01', false, 'Alpha Task Two', '2027-12-01', 20, 200, 1, 1, 2, 5);
