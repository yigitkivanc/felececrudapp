CREATE DATABASE employeedb;

USE employeedb;

CREATE TABLE PersonalInformation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    birth_date DATE,
    national_id VARCHAR(255) NOT NULL,
    military_status ENUM('DEFERRED', 'EXEMPT', 'COMPLETED', 'SERVING'),
    gender ENUM('MALE', 'FEMALE', 'OTHER'),
    marital_status ENUM('MARRIED', 'SINGLE')
);

CREATE TABLE OtherInformation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    bank_name VARCHAR(255) NOT NULL,
    iban VARCHAR(255) NOT NULL,
    emergency_contact_name VARCHAR(255) NOT NULL,
    emergency_contact_phone_number VARCHAR(255) NOT NULL
);

CREATE TABLE Employee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    levell ENUM('L0', 'L1', 'L2', 'L3', 'L4', 'L5'),
    phone_number VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    manager_id BIGINT,
    birth_date DATE,
    work_type ENUM('REMOTE', 'OFFICE', 'HYBRID'),
    contract_type ENUM('FIXED', 'PERMANENT'),
    team ENUM('JAVA', 'ANGULAR', 'C4C', 'ABAP', 'DEVOPS', 'BASIS'),
    start_date DATE,
    end_date DATE,
    personal_information_id BIGINT,
    other_information_id BIGINT,
    CONSTRAINT FK_Manager FOREIGN KEY (manager_id) REFERENCES Manager(id),
    CONSTRAINT FK_PersonalInformation FOREIGN KEY (personal_information_id) REFERENCES PersonalInformation(id),
    CONSTRAINT FK_OtherInformation FOREIGN KEY (other_information_id) REFERENCES OtherInformation(id)
);

CREATE TABLE Manager (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (employee_id) REFERENCES Employee(id)
);

CREATE TABLE Project (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(255) NOT NULL,
    project_type ENUM('PROJECT', 'SUPPORT', 'INTERNAL_PROJECT', 'HR', 'SALES', 'PRODUCT'),
    department VARCHAR(255) NOT NULL,
    vpn_username VARCHAR(255) NOT NULL,
    vpn_password VARCHAR(255) NOT NULL,
    environment_details VARCHAR(255) NOT NULL,
    employee_id BIGINT,
    CONSTRAINT FK_EmployeeProject FOREIGN KEY (employee_id) REFERENCES Employee(id)
);

 
