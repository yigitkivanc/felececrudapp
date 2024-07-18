DROP SCHEMA IF EXISTS `felece-crud`;

CREATE SCHEMA `felece-crud`;

use `felece-crud`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    manager_id INT,
    levell ENUM('L0', 'L1', 'L2', 'L3', 'L4', 'L5'),
    phone_number VARCHAR(15) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    birth_date VARCHAR(30),
    work_type ENUM('Uzaktan', 'Evden', 'Hibrit'),
    contract_type ENUM('Süreli', 'Süresiz'),
    team ENUM('Java', 'Angular', 'C4C', 'Abap', 'DevOPS', 'Basis'),
    start_date VARCHAR(30),
    end_date VARCHAR(30),
    personal_information_id INT,
    other_information_id INT,
    project_id INT,
    FOREIGN KEY (manager_id) REFERENCES manager(manager_id),
    FOREIGN KEY (personal_information_id) REFERENCES personal_information(id),
    FOREIGN KEY (other_information_id) REFERENCES other_information(id),
    FOREIGN KEY (project_id) REFERENCES project(project_id)
);
CREATE TABLE personal_information (
    id INT AUTO_INCREMENT PRIMARY KEY,
    birth_day VARCHAR(30),
    national_id VARCHAR(11),
    military_status ENUM('Tecilli', 'Muaf', 'Yapıldı', 'Görevde'),
    gender ENUM('Erkek', 'Kadın', 'Diğer'),
    marital_status ENUM('Evli', 'Bekar')
);
CREATE TABLE other_information (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_address VARCHAR(255),
    bank_name VARCHAR(100),
    iban VARCHAR(34),
    emergency_contact_name VARCHAR(100),
    emergency_contact_phone VARCHAR(15)
);
CREATE TABLE project (
    project_id int AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(100),
    project_type ENUM('Proje', 'Destek', 'İç Proje', 'IK', 'Satış', 'Ürün'),
    department VARCHAR(100),
    vpn_username VARCHAR(100),
    vpn_password VARBINARY(256), -- Şifrelenmiş alan
    environment_info VARCHAR(255)
    
);

CREATE TABLE manager (
    manager_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    levell ENUM('L0', 'L1', 'L2', 'L3', 'L4', 'L5'),
    phone_number VARCHAR(15) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    birth_date VARCHAR(30),
    work_type ENUM('Uzaktan', 'Evden', 'Hibrit'),
    contract_type ENUM('Süreli', 'Süresiz'),
    team ENUM('Java', 'Angular', 'C4C', 'Abap', 'DevOPS', 'Basis'),
    start_date VARCHAR(30),
    end_date VARCHAR(30),
    personal_informations_id INT,
    other_informations_id INT,
    project_id INT,
    FOREIGN KEY (personal_informations_id) REFERENCES personal_information(id),
    FOREIGN KEY (other_informations_id) REFERENCES other_information(id),
	FOREIGN KEY (manager_id) REFERENCES employee(employee_id)

);
CREATE TABLE `manager_project` (
  `manager_id` int NOT NULL,
  `project_id` int NOT NULL,
  
  PRIMARY KEY (`manager_id`,`project_id`),
  
  KEY `FK_MANAGER_idx` (`manager_id`),
  
  CONSTRAINT `FK_PROJECT` FOREIGN KEY (`project_id`) 
  REFERENCES `project` (`project_id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_MANAGER` FOREIGN KEY (`manager_id`) 
  REFERENCES `manager` (`manager_id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `employee_project` (
  `employee_id` int NOT NULL,
  `project_id` int NOT NULL,
  
  PRIMARY KEY (`employee_id`,`project_id`),
  FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
  FOREIGN KEY (project_id) REFERENCES project(project_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
