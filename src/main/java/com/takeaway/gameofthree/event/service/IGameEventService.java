package com.takeaway.gameofthree.event.service;

/**
 * Service class for creating game move events
 * @author Bharadwaj.Adepu
 *
 */
public interface IGameEventService {

	/**
	 * Sends an event to the opponent player's queue
	 * @param gameId
	 * @param moveId
	 * @param player
	 */
	public void send(int gameId, int moveId, String player);
}
