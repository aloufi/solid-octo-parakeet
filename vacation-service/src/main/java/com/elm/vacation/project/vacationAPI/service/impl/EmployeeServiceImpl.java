package com.elm.vacation.project.vacationAPI.service.impl;

import com.elm.vacation.project.vacationAPI.domain.Department;
import com.elm.vacation.project.vacationAPI.domain.DepartmentEmployee;
import com.elm.vacation.project.vacationAPI.domain.DepartmentManager;
import com.elm.vacation.project.vacationAPI.domain.Employee;
import com.elm.vacation.project.vacationAPI.model.EmployeeDomain;
import com.elm.vacation.project.vacationAPI.repository.EmployeeRepository;
import com.elm.vacation.project.vacationAPI.repository.ManagerRepository;
import com.elm.vacation.project.vacationAPI.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    private final ManagerRepository managerRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ManagerRepository managerRepository) {
        this.employeeRepository = employeeRepository;
        this.managerRepository = managerRepository;
    }


    @Override
    public EmployeeDomain findEmployeeByEmployeeNumber(int id) {
        EmployeeDomain employeeDomain = new EmployeeDomain();
        Employee employee = employeeRepository.getEmployeeByEmployeeNumber(id);
        Set<DepartmentEmployee> departmentEmployee = employee.getDepartmentEmployees();
        employeeDomain.setEmployee(employee);
        DepartmentManager departmentManager = getByEmployeeNumber(employee.getEmployeeNumber());
        if (departmentManager == null) {
            employeeDomain.setDepartmentManager(findTopByDepartmentNumber(departmentEmployee.iterator().next().getDepartment().getDepartmentNumber()));
        }
        return employeeDomain;
    }

    @Override
    public DepartmentManager getByEmployeeNumber(int id) {
        return managerRepository.getByEmployeeNumber(id);
    }

    @Override
    public DepartmentManager findTopByDepartmentNumber(String dept_no) {
        return managerRepository.findTopByDepartmentNumber(dept_no);
    }

    /*@Override
    public EmployeeDomain getEmployeeByEmployeeNumber(int id) {
        EmployeeDomain employeeDomain = new EmployeeDomain();
        Employee employee = employeeRepository.getEmployeeByEmployeeNumber(id);
        Set<DepartmentEmployee> departmentEmployee = employee.getDepartmentEmployees();
        employeeDomain.setEmployee(employee);
        DepartmentManager departmentManager = getByEmployeeNumber(employee.getEmployeeNumber());
        if (departmentManager == null) {
            employeeDomain.setDepartmentManager(findTopByDepartmentNumber(departmentEmployee.iterator().next().getDepartment().getDepartmentNumber()));
        }
        return employeeDomain;
    }*/
}

















