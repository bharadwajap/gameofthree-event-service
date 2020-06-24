package com.takeaway.gameofthree.event.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Bharadwaj.Adepu
 *
 */

@Data
@NoArgsConstructor
public class GameMoveEvent {

	private int gameId;
	private int moveId;
	private String moveBy;
	
	public GameMoveEvent(int gameId, int moveId, String moveBy) {
		this.gameId = gameId;
		this.moveId = moveId;
		this.moveBy = moveBy;
	}
	
	@Override
	public String toString() {
		return String.format("GameMoveEvent [moveBy= %s , gameId= %d, moveId= %d]", moveBy, gameId, moveId);
	}
}
