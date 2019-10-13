package com.elm.vacation.project.vacationAPI.Controller;


import com.elm.vacation.project.vacationAPI.config.RabbitMqConfigReader;
import com.elm.vacation.project.vacationAPI.domain.*;
import com.elm.vacation.project.vacationAPI.model.EmployeeDomain;
import com.elm.vacation.project.vacationAPI.model.Status;
import com.elm.vacation.project.vacationAPI.service.EmployeeService;
import com.elm.vacation.project.vacationAPI.service.VacationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@EnableDiscoveryClient
public class MainController {

    private static Logger log = LoggerFactory.getLogger(MainController.class);

    private final EmployeeService employeeService;

    private final VacationService vacationService;

    private RabbitMqConfigReader rabbitMqConfigReader;


    public RabbitMqConfigReader getApplicationConfig() {
        return rabbitMqConfigReader;
    }

    @Autowired
    public void setApplicationConfig(RabbitMqConfigReader rabbitMqConfigReader) {
        this.rabbitMqConfigReader = rabbitMqConfigReader;
    }

    public MainController(EmployeeService employeeService, VacationService vacationService) {
        this.employeeService = employeeService;
        this.vacationService = vacationService;
    }


    @GetMapping(value = "/test")
    public String test() {
        return "Hi MicroService";
    }

    @RequestMapping(method= RequestMethod.GET, value = "/getEmployee/{id}", produces = "application/json")
    public EmployeeDomain getEmpByID(@PathVariable(value = "id") int id) {
        return employeeService.findEmployeeByEmployeeNumber(id);
    }


    @PostMapping(value = "/createNewVacationRequest", produces = "application/json")
    public void vacationRequest(@RequestBody Vacation vacation) {
        String exchange = getApplicationConfig().getEmployeeRequestExchangeName();
        String routingKey = getApplicationConfig().getEmployeeRequestRoutingKeyName();
        vacationService.sendMassageToRabbitMq(exchange, routingKey, vacation);
    }

    @GetMapping(value = "/VacationEmployeeDetails/{id}", produces = "application/json")
    public List<Vacation> getEmployeeDetails(@PathVariable(value = "id") int id) {
        Integer empId = id;
        return vacationService.getAllByEmployeeNumberOrManagerNumber(id, empId);
    }

    @PutMapping(value = "/updateVacationRequest", produces = "application/json")
    public void vacationResponse(@RequestBody Vacation vacation) {
        String exchange = getApplicationConfig().getManagerRequestExchangeName();
        String routingKey = getApplicationConfig().getManagerRequestRoutingKeyName();
        vacationService.sendMassageToRabbitMq(exchange, routingKey, vacation);
    }

}
