DROP SCHEMA IF EXISTS estimatea;
CREATE SCHEMA estimatea;
USE estimatea;

-- ROLE
CREATE TABLE IF NOT EXISTS role(
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    role_type VARCHAR(60),
    role_rate INT
    );

-- RESSOURCE (Links to Junction TABLE ressource_task)
CREATE TABLE IF NOT EXISTS ressource(
    res_id INT AUTO_INCREMENT PRIMARY KEY,
    res_name VARCHAR(60),
    res_rate INT
    );

-- EMPLOYEE TABLE (Links to Project & Project_employee)
CREATE TABLE IF NOT EXISTS employee(
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(60),
    employee_username VARCHAR(60) NOT NULL UNIQUE,
    employee_password VARCHAR(60) NOT NULL UNIQUE,
    role_id INT,
    FOREIGN KEY (role_id) REFERENCES role(role_id)
    );

-- PROJECT TABLE (Has project_employees and links to subproject and task)
CREATE TABLE IF NOT EXISTS project(
    project_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(60) UNIQUE,
    start_date DATE,
    completed boolean,
    sum_time INT,
    sum_price INT,
    deadline DATE,
    project_manager INT,
    FOREIGN KEY (project_manager) REFERENCES employee(employee_id)
    );

-- PROJECT_EMPLOYEE (Links to Project & Junction TABLE sub_project_emloyee)
CREATE TABLE IF NOT EXISTS project_employee(
    project_employee_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    project_id INT,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE
    );

-- SUBPROJECT (Part of Project and links to task & Junction TABLE sub_project__employee)
CREATE TABLE IF NOT EXISTS subproject(
    sub_id INT AUTO_INCREMENT PRIMARY KEY,
    start_date DATE,
    completed boolean,
    sub_name VARCHAR(60),
    project_id INT,
    FOREIGN KEY (project_id) REFERENCES project(project_id)
    );

-- SUBPROJECT_EMPLOYEE (Junction TABLE and has PK FK (employee_id, sub_id))
CREATE TABLE IF NOT EXISTS sub_project_employee(
    project_employee_id INT,
    sub_id INT,
    PRIMARY KEY (project_employee_id, sub_id),
    CONSTRAINT fk_project_employee FOREIGN KEY (project_employee_id) REFERENCES project_employee(project_employee_id) ON DELETE CASCADE,
    CONSTRAINT fk_subproject FOREIGN KEY (sub_id) REFERENCES subproject(sub_id) ON DELETE CASCADE
    );

-- TASK (Part of project & subproject (Links to Junction TABLE ressource_task))
CREATE TABLE IF NOT EXISTS task(
    task_id INT AUTO_INCREMENT PRIMARY KEY,
    start_date DATE,
    completed boolean,
    task_name VARCHAR(60),
    deadline DATE,
    task_time INT,
    task_price INT,
    project_id INT NOT NULL,
    subproject_id INT,
    ressource_id INT,
    FOREIGN KEY (project_id) REFERENCES project(project_id),
    FOREIGN KEY (subproject_id) REFERENCES subproject(sub_id),
    FOREIGN KEY (ressource_id) REFERENCES ressource(res_id)
    );

-- ESTIMATES (Gives a complexity score to the given task, meant to estimate the difficulty of a task and)
CREATE TABLE IF NOT EXISTS complexity(
    complexity_id INT AUTO_INCREMENT PRIMARY KEY,
    complexity_score INT
    );

-- TASK_COMPLEXITY_JUNCTION (Junction TABLE and has PK FK (task_id, complexity_id)
CREATE TABLE IF NOT EXISTS task_complexity(
    task_complexity_id INT AUTO_INCREMENT NOT NULL,
    task_id INT REFERENCES task(task_id),
    complexity_id INT REFERENCES complexity(complexity_id),
    PRIMARY KEY (task_id, complexity_id)
);

-- RESSOURCE_TASK_JUNCTION (Junction TABLE and has PK FK (task_id, res_id))
CREATE TABLE IF NOT EXISTS ressource_task(
    task_id INT REFERENCES task(task_id),
    res_id INT REFERENCES ressource(res_id),
    PRIMARY KEY (task_id, res_id)
    );


