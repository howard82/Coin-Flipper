package au.edu.rmit.cpt222.model.interfaces;

import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;

/**
 * Assignment interface for SADI to notify client of GameEngine events.
 * 
 * @author Mikhail Perepletchikov and Caspar Ryan
 * 
 */
public interface GameEngineCallback {

	/**
	 * Called as the coin is "rotating" after a flip. Use this to update your
	 * GUI display for each coin flip or log to console.
	 * 
	 * @param coinFace
	 *            the current (upfacing) face of the "rotating" coin
	 * @param engine
	 *            a convenience reference to the engine so the receiver can call
	 *            methods if necessary
	 * @see au.edu.rmit.cpt222.model.interfaces.GameEngine
	 */
	public void coinFlip(Coin.Face coinFace, GameEngine engine);

	/**
	 * Called when the coin stopped "rotating". Use this to update your GUI
	 * display for each coin flip or log to console.
	 * 
	 * @param coinNumber
	 *            the number of this coin in the coin "series"
	 * @param coinFace
	 *            the final coin face resulted from a "flip"
	 * @param engine
	 *            a convenience reference to the engine so the receiver can call
	 *            methods if necessary
	 * @see au.edu.rmit.cpt222.model.interfaces.GameEngine
	 */
	public void coinFlipOutcome(int coinNumber, Coin.Face coinFace,
			GameEngine engine);

	/**
	 * Called for each Player to indicate the outcome of the current game. Use
	 * this to update your GUI display or log to console.
	 * 
	 * @param player
	 *            the Player who is playing the game
	 * @param result
	 *            indicates game outcome - won/lost/drew
	 * @param engine
	 *            a convenience reference to the engine so the receiver can call
	 *            methods if necessary
	 * @see au.edu.rmit.cpt222.model.interfaces.GameEngine
	 */
	public void gameResult(Player player, GameStatus result, GameEngine engine);
}
