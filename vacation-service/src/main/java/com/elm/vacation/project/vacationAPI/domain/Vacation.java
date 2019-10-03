package com.elm.vacation.project.vacationAPI.domain;


import com.elm.vacation.project.vacationAPI.model.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "vacations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vacation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_no")
    private Integer vacationNumber;


    @Column(name = "employee_no")
    private int employeeNumber;

    @Column(name = "manger_no")
    private int managerNumber;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;


    @Column(name = "request_date")
    private Date requestDate;

    @Column(name = "response_date")
    private Date responseDate;

    public Vacation() {
    }

    public Vacation(int employeeNumber, int managerNumber, Status status) {
        this.employeeNumber = employeeNumber;
        this.managerNumber = managerNumber;
        this.status = status;
    }

    public Integer getVacationNumber() {
        return vacationNumber;
    }

    public void setVacationNumber(Integer vacationNumber) {
        this.vacationNumber = vacationNumber;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public int getManagerNumber() {
        return managerNumber;
    }

    public void setManagerNumber(int managerNumber) {
        this.managerNumber = managerNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }
}


