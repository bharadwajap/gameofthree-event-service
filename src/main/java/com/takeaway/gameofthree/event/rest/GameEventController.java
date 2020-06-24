package com.takeaway.gameofthree.event.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.takeaway.gameofthree.event.model.GameMoveEvent;
import com.takeaway.gameofthree.event.service.GameEventProducer;

@RestController
@RequestMapping(value = "/gameofthree/events/")
public class GameEventController {

	@Autowired
	private GameEventProducer evenProducer;


	@PostMapping(value = "/{gameId}/move/{moveId}")
	public String createGameMoveEvent(@PathVariable("gameId") Integer gameId, 
			@PathVariable(name = "moveId") Integer moveId, 
			@RequestParam("player") String player) {

		evenProducer.send(new GameMoveEvent(gameId, moveId, player));

		return "Message sent to the RabbitMQ Successfully";
	}
}
