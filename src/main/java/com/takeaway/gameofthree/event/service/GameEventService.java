package com.takeaway.gameofthree.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.takeaway.gameofthree.event.model.GameMoveEvent;

/**
 * Implementation for the Event service
 * @author Bharadwaj.Adepu
 *
 */
@Service
public class GameEventService implements IGameEventService{
	
	@Autowired
	private GameEventProducer eventProducer;

	@Override
	public void send(int gameId, int moveId, String player) {
		GameMoveEvent moveEvent = new GameMoveEvent(gameId, moveId, player);
		eventProducer.createEvent(moveEvent);
	}
}
