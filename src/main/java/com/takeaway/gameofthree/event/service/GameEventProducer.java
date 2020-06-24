package com.takeaway.gameofthree.event.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.takeaway.gameofthree.event.model.GameMoveEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * Event Producer class
 * @author Bharadwaj.Adepu
 *
 */
@Slf4j
@Component
public class GameEventProducer {
	
	private enum PLAYER{PLAYER1, PLAYER2}
	
	@Value("${config.rabbitmq.routingkey.player1}")
	private String player1RoutingKey;

	@Value("${config.rabbitmq.routingkey.player2}")
	private String player2RoutingKey;
	
	private AmqpTemplate rabbitCustomTemplate;

	public GameEventProducer() {}
	
	@Autowired
	public GameEventProducer(AmqpTemplate rabbitCustomTemplate) {
		this.rabbitCustomTemplate = rabbitCustomTemplate;
	}
	
	/**
	 * Creates an event with the move made by the player and sends to the opponent queue.
	 * @param moveEvent
	 */
	public void createEvent(GameMoveEvent moveEvent) {
		if(moveEvent.getMoveBy().equalsIgnoreCase(PLAYER.PLAYER2.name()))
			rabbitCustomTemplate.convertAndSend(player1RoutingKey, moveEvent);
		else
			rabbitCustomTemplate.convertAndSend(player2RoutingKey, moveEvent);
		log.info("Event created for GapmeMove = {} by Player: {}", moveEvent, moveEvent.getMoveBy());
	}
}
