﻿# Felece FULL CRUD App

This project is an internship project at Felece, designed to manage employee data, including personal information, other information, and project assignments. The system is built using Java Spring Boot and integrates various components such as JPA repositories, services, DTOs, and controllers to provide a robust backend solution.

# Features
 Employee Management: Add, update, and manage employee details including personal and other information.
 
 Manager Assignment: Assign managers to employees and manage hierarchical relationships.
 
 Project Management: Create and update projects, and assign employees and managers to projects.
 
 Data Validation: Ensures unique fields like phone numbers, email addresses, project names, and VPN usernames.
 
 Database Integration: Uses MySQL for data persistence with proper entity relationships and validations.


# Technologies Used
 Java Spring Boot: Backend framework.
 Spring Data JPA: For database interactions.
 Hibernate: ORM tool.
 MySQL: Relational database.
 Maven: Dependency management.
 DTOs and Mappers: For data transfer and mapping between entities and DTOs.
 Validations: Ensuring data integrity and uniqueness.


# Endpoints

# Employee Endpoints

 Create Employee: POST /api/employees/createEmployee
 Update Employee: PUT /api/employees/updateEmployee/{id}
 Get Employee by ID: GET /api/employees//listEmployees/{id}
 Get All Employees: GET /api/employees/listEmployees
 Delete Employee: DELETE /api/employees/deleteEmployee/{id}
 Assign Manager To Employee: POST /api/employees/{employeeId}/assignManager/{managerId}

# Project Endpoints

 Create Project: POST /api/projects/createProject
 Update Project: PUT /api/projects/updateProject/{id}
 Get Project by ID: GET /api/projects/{id}
 Get All Projects: GET /api/projects/listProjects
 Delete Project: DELETE /api/projects/deleteProject/{id}
 Add Employees and Manager to Project: POST /api/projects/{projectId}/addEmployeesAndManager
 Assign Project to Manager: POST /api/projects/{projectId}/assignManager
