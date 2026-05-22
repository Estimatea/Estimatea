-- Leadership and Management
INSERT INTO role (role_type, role_rate) VALUES
('Project Manager', 1000),
('Subproject Manager', 800),
('Program Manager', 500),
('Scrum Master', 200),
('Product Owner', 1),
-- Technical
('Solution/Enterprise Architect', 300),
('Tech Lead', 500),
('Software Developer/Engineer', 300),
('DevOps/Infrastructure Engineer', 300),
('Database Administrator', 500),
('Security Engineer', 300),
-- Quality & Testing
('QA Engineer/Test Analyst', 300),
('Integration Specialist', 450),
-- Analysis & Design
('Business Analyst', 350),
('UX/UI Designer', 200),
('Systems Analyst', 250),
-- Support & Operations
 ('Change Manager', 200),
 ('It Support/Help Desk', 250),
 ('Release Manager', 400);

INSERT INTO complexity (complexity_score, label_type, rate_multiplier) VALUES
                        (1,  'Standard',          1.0),
                        (2,  'Low Complexity',    1.1),
                        (3,  'Minor',             1.2),
                        (4,  'Moderate',          1.3),
                        (5,  'Elevated',          1.5),
                        (6,  'High Complexity',   1.7),
                        (7,  'Advanced',          2.0),
                        (8,  'Critical',          2.3),
                        (9,  'Expert Level',      2.7),
                        (10, 'Enterprise Grade',  3.0);

INSERT INTO employee (employee_name, employee_username, employee_password, role_id)
VALUES ('Joakim', 'Joes', '123', 1);

INSERT INTO employee (employee_name, employee_username, employee_password, role_id)
VALUES ('Simon', 'Sich', '007', 2);

INSERT INTO employee (employee_name, employee_username, employee_password, role_id)
VALUES ('Emil', 'Eman', '010', 3);

INSERT INTO project (project_name, start_date, completed, deadline, project_manager)
VALUES ('Project calculation tool', '2026-01-01', false, '2026-05-28', 1);

INSERT INTO subproject (sub_name, start_date, deadline, sum_time,  sum_price, completed, project_id)
VALUES ('Turbo Rocket Engine Integration', '2026-02-01', '2026-03-01', 10 , 20,false, 1);

INSERT INTO task (start_date, completed, task_name, deadline, task_time, sub_id, current_complexity_id)
VALUES ('2026-02-01', false, 'Pruning of Code', '2026-12-01', 10, 1, 3);

INSERT INTO project_employee (employee_id, project_id) VALUES (1,1), (2,1), (3,1);

INSERT INTO sub_project_employee (project_employee_id, sub_id)  VALUES (1,1), (2,1), (3,1);



