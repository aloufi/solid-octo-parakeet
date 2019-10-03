package com.elm.vacation.project.vacationAPI.service;

import com.elm.vacation.project.vacationAPI.domain.DepartmentManager;
import com.elm.vacation.project.vacationAPI.domain.Employee;
import com.elm.vacation.project.vacationAPI.model.EmployeeDomain;

public interface EmployeeService {

    EmployeeDomain findEmployeeByEmployeeNumber(int id);
    DepartmentManager getByEmployeeNumber(int id);
    DepartmentManager findTopByDepartmentNumber(String dept_no);

}
