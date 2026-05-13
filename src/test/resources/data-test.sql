-- 1. Roles
MERGE INTO role (role_id, role_type, role_rate)
    VALUES (1, 'Project Manager', 1000);

-- 2. Employees
MERGE INTO employee (employee_id, employee_name, employee_username, employee_password, role_id)
    VALUES (1, 'Joakim', 'joakim_dev', 'hashed_password_123', 1);

-- 3. Projects
MERGE INTO project (project_id, project_name, start_date, deadline, project_manager)
    VALUES (1, 'Alpha Solutions', '2026-01-01', '2026-12-31', 1);

-- 4. Subprojects
MERGE INTO subproject (sub_id, sub_name, start_date, deadline, completed, project_id)
    VALUES (1, 'Project calculation tool', '2026-05-01', '2026-05-028', false, 1);

-- 5. Complexity Scores
MERGE INTO complexity (complexity_id, complexity_score, label_type, rate_multiplier)
    VALUES (1, 5, 'High', 1.5);
