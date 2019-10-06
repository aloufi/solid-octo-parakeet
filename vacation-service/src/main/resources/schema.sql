DROP DATABASE IF EXISTS employee;
CREATE DATABASE IF NOT EXISTS employee;
USE employee;

SELECT 'CREATING DATABASE STRUCTURE' as 'INFO';

DROP TABLE IF EXISTS dept_emp,
dept_manager,
titles,
salaries,
employees,
departments;

/*!50503 set default_storage_engine = InnoDB */;
/*!50503 select CONCAT('storage engine: ', @@default_storage_engine) as INFO */;

CREATE TABLE employees (
  emp_no      INT             NOT NULL,
  birth_date  DATE            NOT NULL,
  first_name  VARCHAR(14)     NOT NULL,
  last_name   VARCHAR(16)     NOT NULL,
  gender      ENUM ('M','F')  NOT NULL,
  hire_date   DATE            NOT NULL,
  PRIMARY KEY (emp_no)
);


CREATE TABLE departments (
  dept_no     CHAR(4)         NOT NULL,
  dept_name   VARCHAR(40)     NOT NULL,
  PRIMARY KEY (dept_no),
  UNIQUE  KEY (dept_name)
);


CREATE TABLE dept_manager (
  employee_emp_no       INT             NOT NULL,
  department_dept_no      CHAR(4)         NOT NULL,
  from_date    DATE            NOT NULL,
  to_date      DATE            NOT NULL,
  FOREIGN KEY (employee_emp_no)  REFERENCES employees (emp_no)    ON DELETE CASCADE,
  FOREIGN KEY (department_dept_no) REFERENCES departments (dept_no) ON DELETE CASCADE,
  PRIMARY KEY (employee_emp_no,department_dept_no)
);


CREATE TABLE dept_emp (
  employee_emp_no      INT             NOT NULL,
  department_dept_no     CHAR(4)         NOT NULL,
  from_date   DATE            NOT NULL,
  to_date     DATE            NOT NULL,
  FOREIGN KEY (employee_emp_no)  REFERENCES employees   (emp_no)  ON DELETE CASCADE,
  FOREIGN KEY (department_dept_no) REFERENCES departments (dept_no) ON DELETE CASCADE,
  PRIMARY KEY (employee_emp_no,department_dept_no)
);


CREATE TABLE vacations (
  vacation_no INT             NOT NULL auto_increment,
  employee_no      INT             NOT NULL,
  manger_no      INT             NOT NULL,
  status      ENUM ('PENDING','APPROVED','REJECTED')  NOT NULL,
  request_date   DATE            NOT NULL,
  response_date     DATE                 ,
  PRIMARY KEY (vacation_no)
);

CREATE OR REPLACE VIEW dept_emp_latest_date AS
  SELECT employee_emp_no, MAX(from_date) AS from_date, MAX(to_date) AS to_date
  FROM dept_emp
  GROUP BY employee_emp_no;

# shows only the current department for each employee
CREATE OR REPLACE VIEW current_dept_emp AS
  SELECT l.employee_emp_no, department_dept_no, l.from_date, l.to_date
  FROM dept_emp d
         INNER JOIN dept_emp_latest_date l
           ON d.employee_emp_no=l.employee_emp_no AND d.from_date=l.from_date AND l.to_date = d.to_date;

