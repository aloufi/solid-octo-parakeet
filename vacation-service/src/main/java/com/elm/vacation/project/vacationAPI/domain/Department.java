package com.elm.vacation.project.vacationAPI.domain;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "departments")
public class Department {


    @Id
    @Column(name = "dept_no")
    private String departmentNumber;
    @Column(name = "dept_name")
    private String departmentName;


    @OneToMany(mappedBy = "department")
    private List<DepartmentEmployee> departmentEmployees = new ArrayList<>();

    public Department() {
    }

    public List<DepartmentEmployee> getDepartmentEmployees() {
        return departmentEmployees;
    }

    public void setDepartmentEmployees(List<DepartmentEmployee> departmentEmployees) {
        this.departmentEmployees = departmentEmployees;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentNumber='" + departmentNumber + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", departmentEmployees=" + departmentEmployees +
                '}';
    }
}
