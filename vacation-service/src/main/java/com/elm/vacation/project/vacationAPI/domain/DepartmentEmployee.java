package com.elm.vacation.project.vacationAPI.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "dept_emp")
public class DepartmentEmployee implements Serializable {


    @Id
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("departmentEmployees")
    private Employee employee;


    @Id
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("departmentEmployees")
    private Department department;

    @Column(name = "from_date")
    private Date fromDate;
    @Column(name = "to_date")
    private Date toDate;

    public DepartmentEmployee() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepartmentEmployee)) return false;
        DepartmentEmployee that = (DepartmentEmployee) o;
        return Objects.equals(employee, that.employee) &&
                Objects.equals(department, that.department) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(toDate, that.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, department, fromDate, toDate);
    }


    /*
    * toString() shouldn't implemented here */
}
