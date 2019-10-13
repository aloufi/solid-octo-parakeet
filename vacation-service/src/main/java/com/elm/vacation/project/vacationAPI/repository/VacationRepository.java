package com.elm.vacation.project.vacationAPI.repository;

import com.elm.vacation.project.vacationAPI.domain.Vacation;
import com.elm.vacation.project.vacationAPI.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, Integer> {
    List<Vacation> getAllByEmployeeNumberOrManagerNumber(Integer empId, Integer manId);
    List<Vacation> getAllByEmployeeNumberOrManagerNumberAndStatusIs(Integer empId, Integer manId, Status status);
}
