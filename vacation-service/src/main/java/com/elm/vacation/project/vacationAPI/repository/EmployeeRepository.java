package com.elm.vacation.project.vacationAPI.repository;

import com.elm.vacation.project.vacationAPI.domain.DepartmentManager;
import com.elm.vacation.project.vacationAPI.domain.Employee;
import com.elm.vacation.project.vacationAPI.model.EmployeeDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Integer>{
    Employee findEmployeeByEmployeeNumber(int id);
    Employee getEmployeeByEmployeeNumber(int id);
}
