### ESTIMATEA: Project Planning & Price Estimation
Project Date: 2026 | Last Updated: April 30, 2026</br>
Website: https://estimatea-f8c8g2a2g6e7f2bj.swedencentral-01.azurewebsites.net/employee/login

## 🚀 Mission Statement
ESTIMATEA is a specialized IT platform designed for Copenhagen-based, project-driven enterprises. Our mission is to provide a sustainable, modern infrastructure where precise price modeling and accurate time estimation are treated as the core pillars of business success.

## ✨ Key Features
We empower companies to bridge the gap between initial quotes and final delivery through:

Dynamic Price Estimation: Real-time financial forecasting based on project variables.

Time Tracking & Planning: Granular scheduling to ensure deadlines are met without burnout.

Sustainable Architecture: Built to evolve alongside growing IT requirements.

Data-Driven Insights: Leveraging historical data for more accurate future bidding.

## Projektstruktur
.
├── CONTRIBUTING.md
├── Estimatea.iml
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── estimatea
│   │   │               ├── config
│   │   │               │   └── WebConfig.java
│   │   │               ├── controller
│   │   │               │   ├── EmployeeController.java
│   │   │               │   ├── ProjectController.java
│   │   │               │   ├── SubProjectController.java
│   │   │               │   └── TaskController.java
│   │   │               ├── dto
│   │   │               │   └── ErrorDTO.java
│   │   │               ├── EstimateaApplication.java
│   │   │               ├── exception
│   │   │               │   ├── DataAccessException.java
│   │   │               │   ├── DuplicateKeyException.java
│   │   │               │   ├── GlobalExceptionHandler.java
│   │   │               │   ├── IllegalArgumentException.java
│   │   │               │   └── NotFoundException.java
│   │   │               ├── interceptor
│   │   │               │   └── LoginInterceptor.java
│   │   │               ├── model
│   │   │               │   ├── Complexity.java
│   │   │               │   ├── Employee.java
│   │   │               │   ├── Project.java
│   │   │               │   ├── Role.java
│   │   │               │   ├── SubProject.java
│   │   │               │   └── Task.java
│   │   │               ├── repository
│   │   │               │   ├── jdbc
│   │   │               │   │   ├── ComplexityRepository.java
│   │   │               │   │   ├── EmployeeRepository.java
│   │   │               │   │   ├── ProjectRepository.java
│   │   │               │   │   ├── RoleRepository.java
│   │   │               │   │   ├── SubProjectRepository.java
│   │   │               │   │   └── TaskRepository.java
│   │   │               │   └── mapper
│   │   │               │       ├── ComplexityMapper.java
│   │   │               │       ├── EmployeeMapper.java
│   │   │               │       ├── ProjectEmployeeMapper.java
│   │   │               │       ├── ProjectMapper.java
│   │   │               │       ├── RoleMapper.java
│   │   │               │       ├── SubProjectEmployeeMapper.java
│   │   │               │       ├── SubProjectMapper.java
│   │   │               │       └── TaskMapper.java
│   │   │               └── service
│   │   │                   ├── ComplexityService.java
│   │   │                   ├── EmployeeService.java
│   │   │                   ├── ProjectService.java
│   │   │                   ├── RoleService.java
│   │   │                   ├── SubProjectService.java
│   │   │                   └── TaskService.java
│   │   └── resources
│   │       ├── application-dev.properties
│   │       ├── application-prod.properties
│   │       ├── application.properties
│   │       ├── data.sql
│   │       ├── schema.sql
│   │       ├── static
│   │       │   ├── css
│   │       │   │   └── styles.css
│   │       │   └── images
│   │       │       └── estimatea-logo.png
│   │       └── templates
│   │           ├── add-employee-to-project.html
│   │           ├── add-employee-to-subproject.html
│   │           ├── all-projects.html
│   │           ├── completed-projects.html
│   │           ├── create-project.html
│   │           ├── create-subproject.html
│   │           ├── create-task-project.html
│   │           ├── create-task-subproject.html
│   │           ├── edit-project.html
│   │           ├── edit-subproject.html
│   │           ├── edit-task-project.html
│   │           ├── edit-task-subproject.html
│   │           ├── employee-list.html
│   │           ├── error.html
│   │           ├── fragments
│   │           │   ├── footer.html
│   │           │   └── header.html
│   │           ├── login-page.html
│   │           ├── remove-employee-from-project.html
│   │           ├── remove-employee-from-subproject.html
│   │           ├── view-project.html
│   │           └── view-subproject.html
│   └── test
│       ├── java
│       │   └── com
│       │       └── example
│       │           └── estimatea
│       │               ├── controller
│       │               │   ├── EmployeeControllerTest.java
│       │               │   ├── ProjectControllerTest.java
│       │               │   ├── SubProjectControllerTest.java
│       │               │   └── TaskControllerTest.java
│       │               ├── EstimateaApplicationTests.java
│       │               ├── repository
│       │               │   ├── ComplexityRepositoryTest.java
│       │               │   ├── EmployeeRepositoryTest.java
│       │               │   ├── ProjectRepositoryTest.java
│       │               │   ├── RoleRepositoryTest.java
│       │               │   ├── SubProjectRepositoryTest.java
│       │               │   └── TaskRepositoryTest.java
│       │               └── service
│       │                   ├── ComplexityServiceTest.java
│       │                   ├── EmployeeServiceTest.java
│       │                   ├── ProjectServiceTest.java
│       │                   ├── RoleServiceTest.java
│       │                   ├── SubprojectServiceTest.java
│       │                   └── TaskServiceTest.java
│       └── resources
│           ├── application-test.properties
│           ├── data-test.sql
│           └── h2init.sql
├── target
│   ├── classes
│   │   ├── application-dev.properties
│   │   ├── application-prod.properties
│   │   ├── application.properties
│   │   ├── com
│   │   │   └── example
│   │   │       └── estimatea
│   │   │           ├── config
│   │   │           │   └── WebConfig.class
│   │   │           ├── controller
│   │   │           │   ├── EmployeeController.class
│   │   │           │   ├── ProjectController.class
│   │   │           │   ├── SubProjectController.class
│   │   │           │   └── TaskController.class
│   │   │           ├── dto
│   │   │           │   └── ErrorDTO.class
│   │   │           ├── EstimateaApplication.class
│   │   │           ├── exception
│   │   │           │   ├── DataAccessException.class
│   │   │           │   ├── DuplicateKeyException.class
│   │   │           │   ├── GlobalExceptionHandler.class
│   │   │           │   ├── IllegalArgumentException.class
│   │   │           │   └── NotFoundException.class
│   │   │           ├── interceptor
│   │   │           │   └── LoginInterceptor.class
│   │   │           ├── model
│   │   │           │   ├── Complexity.class
│   │   │           │   ├── Employee.class
│   │   │           │   ├── Project.class
│   │   │           │   ├── Role.class
│   │   │           │   ├── SubProject.class
│   │   │           │   └── Task.class
│   │   │           ├── repository
│   │   │           │   ├── jdbc
│   │   │           │   │   ├── ComplexityRepository.class
│   │   │           │   │   ├── EmployeeRepository.class
│   │   │           │   │   ├── ProjectRepository.class
│   │   │           │   │   ├── RoleRepository.class
│   │   │           │   │   ├── SubProjectRepository.class
│   │   │           │   │   └── TaskRepository.class
│   │   │           │   └── mapper
│   │   │           │       ├── ComplexityMapper.class
│   │   │           │       ├── EmployeeMapper.class
│   │   │           │       ├── ProjectEmployeeMapper.class
│   │   │           │       ├── ProjectMapper.class
│   │   │           │       ├── RoleMapper.class
│   │   │           │       ├── SubProjectEmployeeMapper.class
│   │   │           │       ├── SubProjectMapper.class
│   │   │           │       └── TaskMapper.class
│   │   │           └── service
│   │   │               ├── ComplexityService.class
│   │   │               ├── EmployeeService.class
│   │   │               ├── ProjectService.class
│   │   │               ├── RoleService.class
│   │   │               ├── SubProjectService.class
│   │   │               └── TaskService.class
│   │   ├── data.sql
│   │   ├── schema.sql
│   │   ├── static
│   │   │   ├── css
│   │   │   │   └── styles.css
│   │   │   └── images
│   │   │       └── estimatea-logo.png
│   │   └── templates
│   │       ├── add-employee-to-project.html
│   │       ├── add-employee-to-subproject.html
│   │       ├── all-projects.html
│   │       ├── completed-projects.html
│   │       ├── create-project.html
│   │       ├── create-subproject.html
│   │       ├── create-task-project.html
│   │       ├── create-task-subproject.html
│   │       ├── edit-project.html
│   │       ├── edit-subproject.html
│   │       ├── edit-task-project.html
│   │       ├── edit-task-subproject.html
│   │       ├── employee-list.html
│   │       ├── error.html
│   │       ├── fragments
│   │       │   ├── footer.html
│   │       │   └── header.html
│   │       ├── login-page.html
│   │       ├── remove-employee-from-project.html
│   │       ├── remove-employee-from-subproject.html
│   │       ├── view-project.html
│   │       └── view-subproject.html
│   ├── generated-sources
│   │   └── annotations
│   ├── generated-test-sources
│   │   └── test-annotations
│   └── test-classes
│       ├── application-test.properties
│       ├── com
│       │   └── example
│       │       └── estimatea
│       │           ├── controller
│       │           │   ├── EmployeeControllerTest.class
│       │           │   ├── ProjectControllerTest.class
│       │           │   ├── SubProjectControllerTest.class
│       │           │   └── TaskControllerTest.class
│       │           ├── EstimateaApplicationTests.class
│       │           ├── repository
│       │           │   ├── ComplexityRepositoryTest.class
│       │           │   ├── EmployeeRepositoryTest.class
│       │           │   ├── ProjectRepositoryTest.class
│       │           │   ├── RoleRepositoryTest.class
│       │           │   ├── SubProjectRepositoryTest.class
│       │           │   └── TaskRepositoryTest.class
│       │           └── service
│       │               ├── ComplexityServiceTest.class
│       │               ├── EmployeeServiceTest.class
│       │               ├── ProjectServiceTest.class
│       │               ├── RoleServiceTest.class
│       │               ├── SubprojectServiceTest.class
│       │               └── TaskServiceTest.class
│       ├── data-test.sql
│       └── h2init.sql
└── web
    └── WEB-INF
        └── web.xml

