
-- 1. Roles
INSERT INTO role (role_type, role_rate)
VALUES ('Project Manager', 2500),
       ('Subproject Manager', 1500),
       ('Program Manager', 1200);

-- 2. Employees
INSERT INTO employee (employee_name, employee_username, employee_password, role_id)
VALUES ('Joakim', 'joakim_dev', 'password_123', 1),
       ('Emil', 'emil_dev', 'password_456', 2),
       ('Simon', 'simon_dev', 'password_678', 3);

-- 3. Projects
INSERT INTO project (project_name, start_date, deadline, project_manager)
VALUES ('Alpha Solutions', '2026-01-01', '2026-12-31', 1);

-- 4. Subprojects
INSERT INTO subproject (sub_name, start_date, deadline, completed, project_id)
VALUES ('Project calculation tool', '2026-02-01', '2026-03-01', false, 1);

-- 5. Complexity Scores
INSERT INTO complexity (complexity_score, label_type, rate_multiplier)
VALUES (1, 'Standard', 1.0),
       (5, 'Elevated', 1.5),
       (10, 'Enterprise Grade', 3.0);