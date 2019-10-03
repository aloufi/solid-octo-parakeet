package com.elm.vacation.project.vacationAPI.repository;

import com.elm.vacation.project.vacationAPI.domain.DepartmentManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<DepartmentManager, Integer> {
    
    DepartmentManager getByEmployeeNumber(int id);
    DepartmentManager findTopByDepartmentNumber(String dept_no);
}
