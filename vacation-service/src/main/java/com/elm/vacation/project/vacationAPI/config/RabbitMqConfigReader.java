package com.elm.vacation.project.vacationAPI.config;


import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableRabbit
public class RabbitMqConfigReader {

    /*
    *
    @Value("${mangerServiceName}")
	private String serviceName;
	*
	*
	* =test
=localhost
=5672
=guest
=guest



	* */
    @Value("${employeeQueue}")
    private String employeeRequestQueueName;
    @Value("${employeeExchange}")
    private String employeeRequestExchangeName;
    @Value("${employeeRoutingKey}")
    private String employeeRequestRoutingKeyName;
    @Value("${managerQueue}")
    private String managerRequestQueueName;
    @Value("${managerExchange}")
    private String managerRequestExchangeName;
    @Value("${managerRoutingKey}")
    private String managerRequestRoutingKeyName;
    @Value("${RabbitMqVirtualHost}")
    private String vhost;
    @Value("${RabbitMqHost}")
    private String host;
    @Value("${RabbitMqPort}")
    private Integer portNumber;
    @Value("${RabbitMqUsername}")
    private String userName;
    @Value("${RabbitMqPassword}")
    private String password;



    public String getEmployeeRequestQueueName() {
        return employeeRequestQueueName;
    }

    public void setEmployeeRequestQueueName(String employeeRequestQueueName) {
        this.employeeRequestQueueName = employeeRequestQueueName;
    }

    public String getEmployeeRequestExchangeName() {
        return employeeRequestExchangeName;
    }

    public void setEmployeeRequestExchangeName(String employeeRequestExchangeName) {
        this.employeeRequestExchangeName = employeeRequestExchangeName;
    }

    public String getEmployeeRequestRoutingKeyName() {
        return employeeRequestRoutingKeyName;
    }

    public void setEmployeeRequestRoutingKeyName(String employeeRequestRoutingKeyName) {
        this.employeeRequestRoutingKeyName = employeeRequestRoutingKeyName;
    }

    public String getManagerRequestQueueName() {
        return managerRequestQueueName;
    }

    public void setManagerRequestQueueName(String managerRequestQueueName) {
        this.managerRequestQueueName = managerRequestQueueName;
    }

    public String getManagerRequestExchangeName() {
        return managerRequestExchangeName;
    }

    public void setManagerRequestExchangeName(String managerRequestExchangeName) {
        this.managerRequestExchangeName = managerRequestExchangeName;
    }

    public String getManagerRequestRoutingKeyName() {
        return managerRequestRoutingKeyName;
    }

    public void setManagerRequestRoutingKeyName(String managerRequestRoutingKeyName) {
        this.managerRequestRoutingKeyName = managerRequestRoutingKeyName;
    }

    public String getVhost() {
        return vhost;
    }

    public void setVhost(String vhost) {
        this.vhost = vhost;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*@Value("${spring.rabbitmq.virtual-host}")
    private String vhost;
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port-number}")
    private Integer portNumber;
    @Value("${spring.rabbitmq.username}")
    private String userName;
    @Value("${spring.rabbitmq.password}")
    private String password;
    */

}
