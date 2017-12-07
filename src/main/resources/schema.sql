use payroll_test;

DROP TABLE IF EXISTS payslip;
DROP TABLE IF EXISTS absence;
DROP TABLE IF EXISTS contract;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;

CREATE TABLE department(
  department_code VARCHAR(10) CHARSET ascii PRIMARY KEY,
  department_name VARCHAR(255) NOT NULL DEFAULT 'New Department',
  cost_center VARCHAR(10) CHARSET ascii
);

create table employee(
  ssn CHAR(10) CHARSET ascii PRIMARY KEY,
  full_name varchar(255)  not null,
  email varchar(50) charset ascii unique,
  birthday date,
  department_code VARCHAR(10) CHARSET ascii,
  INDEX department_index (department_code),
  CONSTRAINT employee_department_code_fk FOREIGN KEY (department_code) REFERENCES department(department_code)
);

CREATE TABLE role(
  id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  position_name VARCHAR(255) NOT NULL,
  description TEXT,
  salary_range_top INT UNSIGNED NOT NULL DEFAULT 0,
  salary_range_bottom INT UNSIGNED NOT NULL DEFAULT 0
);

CREATE TABLE contract(
  id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  ssn CHAR(10) CHARSET ascii NOT NULL,
  role_id INT UNSIGNED NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE,
  salary INT UNSIGNED NOT NULL DEFAULT 0,
  INDEX ssn_index (ssn),
  CONSTRAINT contract_ssn_fk FOREIGN KEY (ssn) REFERENCES employee(ssn) ON DELETE CASCADE,
  CONSTRAINT contract_role_id_fk FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE TABLE absence(
  id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  leave_type ENUM('VAC','SKL','MTL','UNP') NOT NULL DEFAULT 'VAC',
  amount INT UNSIGNED DEFAULT 0,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL
);

CREATE TABLE payslip(
  period CHAR(4) NOT NULL,
  ssn CHAR(10) CHARSET ascii NOT NULL,
  wage INT UNSIGNED NOT NULL DEFAULT 0,
  bonus INT UNSIGNED NOT NULL DEFAULT 0,
  ssp INT UNSIGNED NOT NULL DEFAULT 0,
  it INT UNSIGNED NOT NULL DEFAULT 0,
  army INT UNSIGNED NOT NULL DEFAULT 0,
  absence INT UNSIGNED,
  PRIMARY KEY payslip_index (period,ssn),
  CONSTRAINT payslip_ssn_fk FOREIGN KEY (ssn) REFERENCES employee(ssn) ON DELETE CASCADE,
  CONSTRAINT payslip_absence_fk FOREIGN KEY (absence) REFERENCES absence(id)
);

#show CHARACTER SET ;
#show COLLATION WHERE charset='utf8';