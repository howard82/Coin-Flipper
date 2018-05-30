package au.edu.rmit.cpt222.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.rmit.cpt222.model.GameEngineCallbackImpl;
import au.edu.rmit.cpt222.model.GameEngineImpl;
import au.edu.rmit.cpt222.model.SimplePlayer;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.Coin;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;

/**
 * Simple console client for SADI Assignment 1
 * 
 * @author Mikhail Perepletchikov and Caspar Ryan
 * 
 */
public class Client {
	private static Logger logger = Logger.getLogger("assignment1");

	public final static int DEFAULT_BET1 = 100;
	public final static int DEFAULT_BET2 = 50;
	public final static int DEFAULT_FLIP_DELAY = 300;
	public final static int DEFAULT_COIN_DELAY = 500;
	public final static int DEFAULT_CREDIT_POINTS1 = 700;
	public final static int DEFAULT_CREDIT_POINTS2 = 500;
	public final static int NUM_OF_COINS = 6;

	public static void main(String args[]) {

		// create two sample players
		Player theGambler = new SimplePlayer("1", "The Gambler",
				DEFAULT_CREDIT_POINTS1);
		Player theCasual = new SimplePlayer("2", "The Casual",
				DEFAULT_CREDIT_POINTS2);

		// Initialize the Model (GameEngine)
		final GameEngine gameEngine = new GameEngineImpl(NUM_OF_COINS);

		// attach sample GameEngineCallbackImpl to the GameEngine
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());

		// add players to the model
		gameEngine.addPlayer(theGambler);
		gameEngine.addPlayer(theCasual);

		// set test bets
		try {
			theGambler.placeBet(Coin.Face.heads, DEFAULT_BET1);
			theCasual.placeBet(Coin.Face.tails, DEFAULT_BET2);
		}
		// test insufficient funds error
		catch (InsufficientFundsException e) {
			logger.log(Level.INFO, e.getMessage());
		}

		// test initial (pre-game) Player state as retrieved from the
		// GameEngine
		for (Player player : gameEngine.getAllPlayers())
			logger.log(Level.INFO, player.toString());

		// play the game
		gameEngine.flip(DEFAULT_FLIP_DELAY, DEFAULT_COIN_DELAY);

		// log final player data (including updated balance) so we can check
		// correctness
		for (Player player : gameEngine.getAllPlayers())
			logger.log(Level.INFO, player.toString());
	}
}
