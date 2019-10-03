package com.elm.vacation.project.vacationAPI.model;

import com.elm.vacation.project.vacationAPI.domain.DepartmentManager;
import com.elm.vacation.project.vacationAPI.domain.Employee;

public class EmployeeDomain {

    private Employee employee;
    private DepartmentManager departmentManager;

    public EmployeeDomain() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public DepartmentManager getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(DepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }
}
