package com.elm.vacation.project.vacationAPI;

import com.elm.vacation.project.vacationAPI.config.RabbitMqConfigReader;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@EnableEurekaClient
public class VacationApiApplication extends SpringBootServletInitializer implements RabbitListenerConfigurer {


	@Autowired
	private RabbitMqConfigReader applicationConfig;

	public RabbitMqConfigReader getApplicationConfig() {
		return applicationConfig;
	}

	public void setApplicationConfig(RabbitMqConfigReader applicationConfig) {
		this.applicationConfig = applicationConfig;
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(VacationApiApplication.class);
	}

	/* This bean is to read the properties file configs */
	@Bean
	public RabbitMqConfigReader applicationConfig() {
		return new RabbitMqConfigReader();
	}
	/* Creating a bean for the Message queue Exchange */
	@Bean
	public DirectExchange getManagerExchange() {
		return new DirectExchange(getApplicationConfig().getManagerRequestExchangeName());
	}
	/* Creating a bean for the Message queue */
	@Bean
	public Queue getManagerQueue() {
		return new Queue(getApplicationConfig().getManagerRequestQueueName(),true,false,false);
	}
	/* Binding between Exchange and Queue using routing key */
	@Bean
	public Binding declareBindingManager() {
		return BindingBuilder.bind(getManagerQueue()).to(getManagerExchange()).with(getApplicationConfig().getManagerRequestRoutingKeyName());
	}
	/* Creating a bean for the Message queue Exchange */
	@Bean
	public DirectExchange getEmployeeExchange() {
		return new DirectExchange(getApplicationConfig().getEmployeeRequestExchangeName());
	}
	/* Creating a bean for the Message queue */
	@Bean
	public Queue getEmployeeQueue() {
		return new Queue(getApplicationConfig().getEmployeeRequestQueueName(),true,false,false);
	}
	/* Binding between Exchange and Queue using routing key */
	@Bean
	public Binding declareBindingEmployee() {
		return BindingBuilder.bind(getEmployeeQueue()).to(getEmployeeExchange()).with(getApplicationConfig().getEmployeeRequestRoutingKeyName());
	}
	/* Bean for rabbitTemplate */
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}
	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(consumerJackson2MessageConverter());
		return factory;
	}
	@Override
	public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
	}


	@Bean
	public com.rabbitmq.client.ConnectionFactory clientConnectionFactory() {
		com.rabbitmq.client.ConnectionFactory connectionFactory = new com.rabbitmq.client.ConnectionFactory();
		connectionFactory.setHost(getApplicationConfig().getHost());
		connectionFactory.setUsername(getApplicationConfig().getUserName());
		connectionFactory.setPassword(getApplicationConfig().getPassword());
		connectionFactory.setPort(getApplicationConfig().getPortNumber());
		connectionFactory.setVirtualHost(getApplicationConfig().getVhost());
		return connectionFactory;
	}

	public static void main(String[] args) {
		SpringApplication.run(VacationApiApplication.class, args);
	}


	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}





}
