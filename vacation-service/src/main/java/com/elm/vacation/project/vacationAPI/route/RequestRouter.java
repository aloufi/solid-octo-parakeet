package com.elm.vacation.project.vacationAPI.route;


import com.elm.vacation.project.vacationAPI.config.RabbitMqConfigReader;
import com.elm.vacation.project.vacationAPI.domain.Vacation;
import com.elm.vacation.project.vacationAPI.service.VacationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestRouter extends RouteBuilder {

    private static Logger log = LoggerFactory.getLogger(RequestRouter.class);

    private final VacationService vacationService;
    private final ObjectMapper objectMapper;

    private RabbitMqConfigReader rabbitMqConfigReader;

    public RabbitMqConfigReader getApplicationConfig() {
        return rabbitMqConfigReader;
    }

    @Autowired
    public void setApplicationConfig(RabbitMqConfigReader rabbitMqConfigReader) {
        this.rabbitMqConfigReader = rabbitMqConfigReader;
    }

    public RequestRouter(VacationService vacationService, ObjectMapper objectMapper) {
        this.vacationService = vacationService;
        this.objectMapper = objectMapper;
    }



    @Override
    public void configure() throws Exception {
        String employeeExchange = getApplicationConfig().getEmployeeRequestExchangeName();
        String managerExchange = getApplicationConfig().getManagerRequestExchangeName();

        String employeeQueue = getApplicationConfig().getEmployeeRequestQueueName();
        String managerQueue = getApplicationConfig().getManagerRequestQueueName();

        String employeeRoutingKey = getApplicationConfig().getEmployeeRequestRoutingKeyName();
        String managerRoutingKey = getApplicationConfig().getManagerRequestRoutingKeyName();

        String employeeRequestQueue   = "rabbitmq:"+employeeExchange+"?queue="+employeeQueue+"&routingKey="+employeeRoutingKey+"&autoDelete=false";
        String managerRequestQueue   = "rabbitmq:"+managerExchange+"?queue="+managerQueue+"&routingKey="+managerRoutingKey+"&autoDelete=false";

        camelProcessor(employeeRequestQueue);

        camelProcessor(managerRequestQueue);
    }

    private void camelProcessor(String requestQueue) {
        from(requestQueue).process(exchange -> {
            String payload = exchange.getIn().getBody(String.class);
            try {
                Vacation vacationRequest = objectMapper.readValue(payload, Vacation.class);
                log.info("payload: " + payload);
                vacationService.save(vacationRequest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
