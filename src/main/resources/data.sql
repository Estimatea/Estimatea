-- Leadership and Management
INSERT INTO role (role_type, role_rate) VALUES
('Project Manager', NULL),
('Subproject Manager', NULL),
('Program Manager', NULL),
('Scrum Master', NULL),
('Product Owner', NULL),
-- Technical
('Solution/Enterprise Architect', NULL),
('Tech Lead', NULL),
('Software Developer/Engineer', NULL),
('DevOps/Infrastructure Engineer', NULL),
('Database Administrator', NULL),
('Security Engineer', NULL),
-- Quality & Testing
('QA Engineer/Test Analyst', NULL),
('Integration Specialist', NULL),
-- Analysis & Design
('Business Analyst', NULL),
('UX/UI Designer', NULL),
('Systems Analyst', NULL),
-- Support & Operations
 ('Change Manager', NULL),
 ('It Support/Help Desk', NULL),
 ('Release Manager', NULL);

INSERT INTO employee (employee_name, employee_username, employee_password, role_id)
VALUES ('Joakim', 'Joes', '123', 1);

INSERT INTO employee (employee_name, employee_username, employee_password, role_id)
VALUES ('Simon', 'Sich', '007', 2);

INSERT INTO employee (employee_name, employee_username, employee_password, role_id)
VALUES ('Emil', 'Eman', '010', 3);

INSERT INTO project (project_name, start_date, completed, sum_time, sum_price, deadline, project_manager)
VALUES ('Project calculation tool', '2026-01-01', false, 1000, 25000, '2026-05-28', 1);

INSERT INTO subproject (sub_name, start_date, deadline, sum_time,  sum_price, completed, project_id)
VALUES ('Project calculation tool', '2026-02-01', '2026-03-01', 10 , 20,false, 1);

INSERT INTO project_employee (employee_id, project_id) VALUES (1,1), (2,1), (3,1);

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

