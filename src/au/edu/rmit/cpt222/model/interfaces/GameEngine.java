package au.edu.rmit.cpt222.model.interfaces;

import java.util.Collection;

import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;

/**
 * Assignment interface (facade) for SADI providing main model functionality
 *
 * @author Mikhail Perepletchikov and Caspar Ryan
 *
 */
public interface GameEngine {

	// enum representing the game outcome for a player
	public enum GameStatus {
		WON, LOST, DREW
	}

	// default number of coins to be used in the game
	public static final int NUM_OF_COINS = 2;

	/**
	 * Adds new GameEngineCallback to the GameEngine
	 * 
	 * @param gameEngineCallback
	 *            a client specific implementation of GameEngineCallback used to
	 *            perform display updates etc. you will use a different
	 *            implementation of the GameEngineCallback for GUI and console
	 *            versions
	 *
	 */
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback);

	/**
	 * Adds a Player to the game
	 * 
	 * @param player
	 *            Player object to add to the game
	 */
	public void addPlayer(Player player);

	/**
	 * This method goes through all players and applies win/loss/draw outcome to
	 * update betting points.
	 * {@link GameEngineCallback#gameResult(Player, GameStatus, GameEngine)}
	 * should also be called with final result for each player. NOTE: you don't
	 * have to call this method from the client. Instead, it can be called
	 * internally from the {@link GameEngine#flip(int, int)}.
	 *
	 * @see au.edu.rmit.cpt222.model.interfaces.GameEngineCallback
	 */
	public void calculateResult();

	/**
	 * Flip the coins
	 *
	 * 1. flip a coin random number of times; 2. call
	 * {@link GameEngineCallback#coinFlip(Coin.Face, GameEngine)} while the coin
	 * is spinning; 3. call
	 * {@link GameEngineCallback#coinFlipOutcome(int, Coin.Face, GameEngine)}
	 * when the coin has stopped spinning; 4. continue to perform coin flip with
	 * a new coin until all coins have been "flipped"; 5. call
	 * {@link GameEngine#calculateResult()}.
	 * 
	 * NOTES: - the value for number of coins to be "flipped" can be supplied by
	 * the client during GameEngineImpl initialisation. - if this number is not
	 * provided, the default GameEngine.NUM_OF_COINS value should be used
	 * instead. - the client must make sure that the provided number of coins
	 * value is even.
	 *
	 * @param flipDelay
	 *            the delay between each coin "spin" in milliseconds
	 * @param coinDelay
	 *            the delay between the coin change in milliseconds
	 * @see au.edu.rmit.cpt222.model.interfaces.GameEngineCallback
	 *
	 */
	public void flip(int flipDelay, int coinDelay);

	/**
	 *
	 * @return an unmodifiable collection of all Players
	 * @see au.edu.rmit.cpt222.model.interfaces.Player
	 */
	public Collection<Player> getAllPlayers();

	/**
	 * Retrieves a Player indicated by the provided id
	 * 
	 * @param id
	 *            id of the Player to retrieve (should return null if not found)
	 * @return Player object
	 */
	public Player getPlayer(String id);

	/**
	 * The implementation should forward the call to the Player entity which
	 * will be responsible for checking if this player has sufficient points to
	 * place the bet
	 * 
	 * @param player
	 *            betting Player
	 * @param face
	 *            the chosen coin face
	 * @param bet
	 *            the bet in points
	 * @throws au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException
	 *             if the player has insufficient points and the bet cannot be
	 *             placed
	 * 
	 */
	public void placeBet(Player player, Coin.Face face, int bet)
			throws InsufficientFundsException;

	/**
	 * Removes a callback associated with the player (when a player quits the
	 * game) to remove no longer needed UI updates
	 * 
	 * TODO (no need to implement in Ass1)
	 * 
	 * @param gameEngineCallback
	 *            a client specific implementation of GameEngineCallback to be
	 *            removed from the game. NOTE: to be used in Assignment 2
	 * 
	 */
	public void removeGameEngineCallback(GameEngineCallback gameEngineCallback);

	/**
	 * Removes a given Player from the game.
	 * 
	 * @param player
	 *            reference to the Player to be removed
	 * @return true if the player existed
	 */
	public boolean removePlayer(Player player);

	/**
	 * 
	 * Sets player's (credit) points to the provided value.
	 * 
	 * @param player
	 *            reference to the Player to be updated with new points
	 * @param totalPoints
	 *            sets player points balance (total points)
	 * 
	 */
	public void setPlayerPoints(Player player, int totalPoints);

}