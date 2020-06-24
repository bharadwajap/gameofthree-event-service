package com.takeaway.gameofthree.event.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Bharadwaj.Adepu
 *
 */
@Configuration
public class GameEventServiceConfig {

	@Value("${config.rabbitmq.queue.player1}")
	private String player1QueueName;

	@Value("${config.rabbitmq.queue.player2}")
	private String player2QueueName;
	
	@Value("${config.rabbitmq.exchange}")
	private String exchange;

	@Value("${config.rabbitmq.routingkey.player1}")
	private String player1RoutingKey;

	@Value("${config.rabbitmq.routingkey.player2}")
	private String player2RoutingKey;
	
	@Bean
	public Queue player1Queue() {
		return new Queue(player1QueueName, true);
	}

	@Bean
	public Queue player2Queue() {
		return new Queue(player2QueueName, true);
	}
	
	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	Binding bindingPlayer1(DirectExchange exchange) {
		return BindingBuilder.bind(player1Queue()).to(exchange).with(player1RoutingKey);
	}

	@Bean
	Binding bindingPlayer2(DirectExchange exchange) {
		return BindingBuilder.bind(player2Queue()).to(exchange).with(player2RoutingKey);
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate rabbitCustomTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		rabbitTemplate.setExchange(exchange);
		return rabbitTemplate;
	}
}