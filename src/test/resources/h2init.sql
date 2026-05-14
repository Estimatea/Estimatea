-- ROLE
CREATE TABLE IF NOT EXISTS role(
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    role_type VARCHAR(60),
    role_rate INT
    );

-- EMPLOYEE TABLE (Links to Project & Project_employee)
CREATE TABLE IF NOT EXISTS employee(
    employee_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(60) NOT NULL,
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
    completed BOOLEAN DEFAULT FALSE,
    sum_time INT,
    sum_price INT,
    deadline DATE,
    project_manager INT,
    FOREIGN KEY (project_manager) REFERENCES employee(employee_id)
    );

-- PROJECT_EMPLOYEE (Links to Project & Junction TABLE sub_project_employee)
CREATE TABLE IF NOT EXISTS project_employee(
    project_employee_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    project_id INT,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE
    );

-- SUBPROJECT (Part of Project and links to task & Junction TABLE sub_project__employee)
CREATE TABLE IF NOT EXISTS subproject(
    sub_id INT AUTO_INCREMENT PRIMARY KEY,
    sub_name VARCHAR(60),
    start_date DATE,
    deadline DATE,
    completed BOOLEAN DEFAULT FALSE,
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

-- ESTIMATES (Gives a complexity score to the given task, meant to estimate the difficulty of a task and)
CREATE TABLE IF NOT EXISTS complexity(
    complexity_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    complexity_score INT NOT NULL,
    label_type VARCHAR(60),
    rate_multiplier DOUBLE NOT NULL
    );

-- TASK (Part of project & subproject (Links to Junction TABLE ressource_task))
CREATE TABLE IF NOT EXISTS task(
    task_id INT AUTO_INCREMENT PRIMARY KEY,
    start_date DATE NOT NULL,
    completed boolean,
    task_name VARCHAR(60),
    deadline DATE NOT NULL,
    task_time INT,
    task_price INT,
    project_id INT NOT NULL,
    subproject_id INT,
    employee_id INT,
    current_complexity_id INT,
    FOREIGN KEY (project_id) REFERENCES project(project_id),
    FOREIGN KEY (subproject_id) REFERENCES subproject(sub_id),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
    );

-- TASK_COMPLEXITY_HISTORY (Links to task and complexity tables. Keeps a history of complexity assigned)
CREATE TABLE IF NOT EXISTS task_complexity_history(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    task_id INT,
    complexity_id INT NOT NULL,
    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    assigned_by INT,
    FOREIGN KEY (task_id) REFERENCES task(task_id),
    FOREIGN KEY (complexity_id) REFERENCES complexity(complexity_id),
    FOREIGN KEY (assigned_by) REFERENCES employee(employee_id)
    );
