package com.takeaway.gameofthree.event.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.takeaway.gameofthree.event.model.GameMoveEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Bharadwaj.Adepu
 *
 */
@Slf4j
@Service
public class GameEventProducer {
	
	private enum PLAYER{PLAYER1, PLAYER2}
	
	@Value("${config.rabbitmq.routingkey.player1}")
	private String player1RoutingKey;

	@Value("${config.rabbitmq.routingkey.player2}")
	private String player2RoutingKey;
	
	@Autowired
	private AmqpTemplate rabbitCustomTemplate;

	public void send(GameMoveEvent moveEvent) {
		if(moveEvent.getMoveBy().equalsIgnoreCase(PLAYER.PLAYER2.name()))
			rabbitCustomTemplate.convertAndSend(player1RoutingKey, moveEvent);
		else
			rabbitCustomTemplate.convertAndSend(player2RoutingKey, moveEvent);
		log.info("Event created for GapmeMove = {} by Player: {}", moveEvent, moveEvent.getMoveBy());
	}
}
