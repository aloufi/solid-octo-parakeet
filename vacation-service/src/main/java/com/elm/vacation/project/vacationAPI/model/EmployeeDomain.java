package com.elm.vacation.project.vacationAPI.model;

import com.elm.vacation.project.vacationAPI.domain.DepartmentManager;
import com.elm.vacation.project.vacationAPI.domain.Employee;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDomain)) return false;
        EmployeeDomain that = (EmployeeDomain) o;
        return Objects.equals(employee, that.employee) &&
                Objects.equals(departmentManager, that.departmentManager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, departmentManager);
    }
}
