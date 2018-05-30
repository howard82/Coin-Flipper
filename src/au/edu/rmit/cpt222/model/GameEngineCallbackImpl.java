package au.edu.rmit.cpt222.model;

import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.rmit.cpt222.model.interfaces.Coin.Face;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GameEngineCallbackImpl implements GameEngineCallback {

	private Logger logger = Logger.getLogger("Assignment1");
	
	@Override
	public void coinFlip(Face coinFace, GameEngine engine) {
		// TODO Auto-generated method stub
		this.logger.log(Level.INFO, "The coin is flipped and come up as " 
				+ coinFace);
	}

	@Override
	public void coinFlipOutcome(int coinNumber, Face coinFace, GameEngine engine) {
		this.logger.log(Level.INFO, "Final coin face for coin number " 
				+ coinNumber + " is " + coinFace);
		
	}

	@Override
	public void gameResult(Player player, GameStatus result, GameEngine engine) {
		// TODO Auto-generated method stub
		this.logger.log(Level.INFO, player.getPlayerName() + " " + result.toString() 
			+ " the game");
	}

}
