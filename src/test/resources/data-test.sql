
-- 1. Roles
INSERT INTO role (role_id, role_type, role_rate)
VALUES (1, 'Project Manager', 1000);

-- 2. Employees
INSERT INTO employee (employee_id, employee_name, employee_username, employee_password, role_id)
VALUES (1, 'Joakim', 'joakim_dev', 'hashed_password_123', 1);

-- 3. Projects
INSERT INTO project (project_id, project_name, start_date, deadline, project_manager)
VALUES (1, 'Main System Build', '2026-01-01', '2026-12-31', 1);

-- 4. Subprojects
INSERT INTO subproject (sub_id, sub_name, start_date, deadline, completed, project_id)
VALUES (1, 'Database Layer', '2026-02-01', '2026-03-01', false, 1);

-- 5. Complexity Scores
INSERT INTO complexity (complexity_id, complexity_score, label_type, rate_multiplier)
VALUES (1, 5, 'High', 1.5);