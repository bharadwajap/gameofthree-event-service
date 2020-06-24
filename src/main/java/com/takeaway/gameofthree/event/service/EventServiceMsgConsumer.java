package com.takeaway.gameofthree.event.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
public class EventServiceMsgConsumer {
	
	//@RabbitListener(queues = "${config.rabbitmq.queue}")
	public void recievedMessage(GameMoveEvent move) {
		log.info("Recieved move From {}: {}", move.getMoveBy(), move);
	}
}
