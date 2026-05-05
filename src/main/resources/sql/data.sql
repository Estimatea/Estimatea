-- Leadership and Management
INSERT INTO role (role_type, role_rate) VALUES
 ("Project Manager", NUll),
 ("Subproject Manager", NUll),
 ("Program Manager", NUll),
 ("Scrum Master", NUll),
 ("Product Owner", NUll),
-- Technical
 ("Solution/Enterprise Architect", NUll),
 ("Tech Lead", NUll),
 ("Software Developer/Engineer", NULL),
 ("DevOps/Infrastructure Engineer", NUll),
 ("Database Administrator", NUll),
 ("Security Engineer", NUll),
-- Quality & Testing
  ("QA Engineer/Test Analyst", NUll),
  ("Integration Specialist", NUll),
-- Analysis & Design
 ("Business Analyst", NUll),
 ("UX/UI Designer", NUll),
 ("Systems Analyst", NUll),
-- Support & Operations
 ("Change Manager", NUll),
 ("It Support/Help Desk", NUll),
 ("Release Manager", NUll);

INSERT INTO employee (employee_name, employee_username, employee_password, role_id)
VALUES ('Joakim', 'Joes', '123', 1);

INSERT INTO employee (employee_name, employee_username, employee_password, role_id)
VALUES ('Simon', 'Sich', '007', 2);

INSERT INTO employee (employee_name, employee_username, employee_password, role_id)
VALUES ('Emil', 'Eman', '010', 3);

INSERT INTO project VALUES (NULL,"Alpha Solutions Projekt Kalkulations Værktøj", 30, 25000, "2026-05-3", 1);

INSERT INTO project_employee (employee_id, project_id) VALUES (1,1), (2,1), (3,1);

INSERT INTO ressource (res_name, res_rate) VALUES ("SAP", 250);
INSERT INTO ressource (res_name, res_rate) VALUES ("Konsulent", 750);
INSERT INTO ressource (res_name, res_rate) VALUES ("Management Tool", 300);

