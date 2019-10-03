package com.elm.vacation.project.vacationAPI.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "dept_manager")
public class DepartmentManager implements Serializable {


    @Id
    @Column(name = "employee_emp_no")
    private int employeeNumber;

    @Column(name = "department_dept_no")
    private String departmentNumber;

    @Column(name = "from_date")
    private Date fromDate;
    @Column(name = "to_date")
    private Date toDate;

    public DepartmentManager() { }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
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
        if (!(o instanceof DepartmentManager)) return false;
        DepartmentManager that = (DepartmentManager) o;
        return employeeNumber == that.employeeNumber &&
                Objects.equals(departmentNumber, that.departmentNumber) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(toDate, that.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNumber, departmentNumber, fromDate, toDate);
    }

    @Override
    public String toString() {
        return "DepartmentManager{" +
                "employeeNumber=" + employeeNumber +
                ", departmentNumber='" + departmentNumber + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
