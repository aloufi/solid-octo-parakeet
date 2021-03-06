package com.elm.vacation.project.vacationAPI.service;

import com.elm.vacation.project.vacationAPI.domain.Vacation;
import com.elm.vacation.project.vacationAPI.model.Status;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VacationService {

    List<Vacation> getAllByEmployeeNumberOrManagerNumber(Integer empId, Integer manId);
    void save(Vacation vacation);
    Vacation getOne(Integer id);
    List<Vacation> getAllByEmployeeNumberOrManagerNumberAndStatusIs(Integer empId, Integer manId, Status status);
    ResponseEntity sendMassageToRabbitMq(String exchange, String routingKey, Vacation updateVacationRequest);
}
