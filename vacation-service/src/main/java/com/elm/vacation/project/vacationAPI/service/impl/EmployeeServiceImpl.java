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
        Employee employee = employeeRepository.findEmployeeByEmployeeNumber(id);
        employeeDomain.setEmployee(employee);

        for (DepartmentEmployee departmentEmployee : employee.getDepartmentEmployees()) {
            Department department = departmentEmployee.getDepartment();
            DepartmentManager departmentManager = getByEmployeeNumber(employee.getEmployeeNumber());
            if (departmentManager == null) {
                employeeDomain.setDepartmentManager(findTopByDepartmentNumber(department.getDepartmentNumber()));
            }
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
}
