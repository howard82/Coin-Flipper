package au.edu.rmit.cpt222.model.interfaces;

import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;

/**
 * Assignment interface for SADI representing the player.
 * 
 * @author Caspar Ryan and Mikhail Perepletchikov
 * 
 */
public interface Player {
	/**
	 * @return the bet as was set with placeBet()
	 */
	public int getBet();

	/**
	 * 
	 * @return the coin face the player is betting on.
	 */
	public Coin.Face getFacePick();

	/**
	 * @return the player ID (can be set initially by the calling i.e. Client
	 *         class)
	 */
	public String getPlayerId();

	/**
	 * @return human readable player name
	 */
	public String getPlayerName();

	/**
	 * 
	 * @return number of points from setPoints()
	 */
	public int getPoints();

	/**
	 * 
	 @return result of the last game played by the player.
	 */
	public GameStatus getResult();

	/**
	 * 
	 * @param facePick
	 *            the coin face the player is betting on.
	 * @param bet
	 *            the bet in points.
	 * @throws au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException
	 *             if the player has insufficient points and the bet cannot be
	 *             placed
	 */
	public void placeBet(Coin.Face facePick, int bet)
			throws InsufficientFundsException;

	/**
	 * @param playerName
	 *            human readable player name
	 */
	public void setPlayerName(String playerName);

	/**
	 * @param points
	 *            set total betting points (updated with each win or loss)
	 */
	public void setPoints(int points);

	/**
	 * 
	 * @param status
	 *            status of the last game played by the player
	 */
	public void setResult(GameStatus status);

	/**
	 * 
	 * @return a human readable String that lists the values of this Player
	 *         instance for debugging or console display
	 */
	@Override
	public String toString();
}
