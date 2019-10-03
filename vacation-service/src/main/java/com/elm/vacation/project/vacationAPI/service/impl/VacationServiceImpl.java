package com.elm.vacation.project.vacationAPI.service.impl;

import com.elm.vacation.project.vacationAPI.config.ApplicationConstant;
import com.elm.vacation.project.vacationAPI.config.RabbitMqConfigReader;
import com.elm.vacation.project.vacationAPI.domain.Vacation;
import com.elm.vacation.project.vacationAPI.model.Status;
import com.elm.vacation.project.vacationAPI.repository.VacationRepository;
import com.elm.vacation.project.vacationAPI.service.VacationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class VacationServiceImpl implements VacationService {

    //private static final Logger log = LoggerFactory.getLogger(VacationService.class);
    private static final Logger log = LoggerFactory.getLogger(VacationServiceImpl.class);

    private final RabbitTemplate rabbitTemplate;

    private final VacationRepository vacationRepository;

    public VacationServiceImpl(RabbitTemplate rabbitTemplate, VacationRepository vacationRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.vacationRepository = vacationRepository;
    }

    @Override
    public List<Vacation> getAllByEmployeeNumberOrManagerNumber(Integer empId, Integer manId) {
        List<Vacation> vacationList = vacationRepository.getAllByEmployeeNumberOrManagerNumber(empId,manId);
        for (Vacation vacation: vacationList) {
            if (vacation.getManagerNumber() == manId) {
                vacationList = getAllByEmployeeNumberOrManagerNumberAndStatusIs(empId, manId, Status.PENDING);
            }
        }
        return vacationList;
    }

    @Override
    public void save(Vacation vacation){
        vacationRepository.save(vacation);
    }

    @Override
    public Vacation getOne(Integer id){ return  vacationRepository.getOne(id); }

    @Override
    public List<Vacation> getAllByEmployeeNumberOrManagerNumberAndStatusIs(Integer empId, Integer manId, Status status) {
        return vacationRepository.getAllByEmployeeNumberOrManagerNumberAndStatusIs(empId, manId, status);
    }

    @Override
    public void sendMassageToRabbitMq(String exchange, String routingKey, Vacation updateVacationRequest) {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, updateVacationRequest);
            rabbitTemplate.convertAndSend(MessageDeliveryMode.PERSISTENT);
            new ResponseEntity<String>(ApplicationConstant.IN_QUEUE, HttpStatus.OK);
        } catch (Exception ex) {
            new ResponseEntity<String>(ApplicationConstant.MESSAGE_QUEUE_SEND_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
