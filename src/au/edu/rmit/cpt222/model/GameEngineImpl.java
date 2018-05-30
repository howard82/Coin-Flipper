package au.edu.rmit.cpt222.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.Coin;
import au.edu.rmit.cpt222.model.interfaces.Coin.Face;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GameEngineImpl implements GameEngine {
	
	protected List<CoinImpl> coins =  new ArrayList<CoinImpl>();
	protected Map<String, Player> players = new ConcurrentHashMap<String, Player>(); //String is PlayerID
	protected Set<GameEngineCallback> callBacks = Collections.newSetFromMap(new ConcurrentHashMap<GameEngineCallback, Boolean>()); 

	public GameEngineImpl() {
		this(NUM_OF_COINS);
	}
	
	public GameEngineImpl(int numOfCoins) {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < numOfCoins; i++)
			this.coins.add(new CoinImpl());
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.callBacks.add(gameEngineCallback);
	}

	@Override
	public void addPlayer(Player player) {
		this.players.put(player.getPlayerId(), player);
	}

	@Override
	public void calculateResult() {
		int headCount = 0;
		int tailsCount = 0;
		Face winFace = null;
		
//		This method goes through all players and applies win/loss/draw outcome to
//		 * update betting points.
//		 * {@link GameEngineCallback#gameResult(Player, GameStatus, GameEngine)}
//		 * should also be called with final result for each player. NOTE: you don't
//		 * have to call this method from the client. Instead, it can be called
//		 * internally from the {@link GameEngine#flip(int, int)}.
		
		// Add up heads and tails results for coins
		for (Coin coin : coins){
			if (coin.getCurrentFace() == Face.heads)
				headCount++;
			if (coin.getCurrentFace() == Face.tails)
				tailsCount++;
		}
		
		// If there was a majority heads or tails, name that face the winner, otherwise Winface is null
		if (headCount > tailsCount){
			winFace = Face.heads;
		}
		else if (tailsCount > headCount){
			winFace = Face.tails;
		}
		
		// iterate through all the game players
		for (Player player : getAllPlayers()){
			// value will be null if face outcomes are even
			if (winFace != null)
				// Check the Face picked by each player and determine if they guessed correctly
				if (player.getFacePick() == winFace){
					// Update players result to a win
					player.setResult(GameStatus.WON);
					// Increase points by value of bet
					player.setPoints(player.getPoints() + player.getBet());
				}
				else{
					// Update players result to a loss
					player.setResult(GameStatus.LOST);
					// Decrease points by value of bet
					player.setPoints(player.getPoints() - player.getBet());
				}	
			else
				//neither heads nor tails won so the game is a draw
				player.setResult(GameStatus.DREW);
			
			/* Notes in the interface appear to say to call GameEngineCallback#gameResult from flip, it
			 * makes more sense to call it here than within flip  so I'm assuming the intent is to call it in calculate
			 * given I've called it from flip and I'm already iterating through all the players and assigning results.
			 * from interface notes: --- NOTE: you don't have to call this method from the client. 
			 * Instead, it can be called internally from the {@link GameEngine#flip(int, int)} 
			 * ---  */
			
			// return results to all clients through callbacks
			for (GameEngineCallback gameEngineCallBack : this.callBacks)
				gameEngineCallBack.gameResult(player, player.getResult(), GameEngineImpl.this);
			}
		}

	@Override
	public void flip(int flipDelay, int coinDelay) {
		// This is here for the second invalid input that is in test 5, however this never runs due 
		// to the invalid place bet input that is above it in the try statement which triggers an 
		// assert error in the 
		assert flipDelay >= 0 && coinDelay >= 0 : "invalid delay";
		
		// declare helper variables
		final int MAX_FLIPS = 10;
		final int MIN_FLIPS = 2;
		int randomFlips;
		CoinImpl coin;
		
		// iterate through all the coins in the game and flip a random number of times
		for (int coinNo = 0; coinNo < this.coins.size(); coinNo++){
			// helper variable
			coin = coins.get(coinNo);
			
			// generate a random number of times to flip current coin
			randomFlips = new Random().nextInt(MAX_FLIPS - MIN_FLIPS) + MIN_FLIPS;
			
			// --- 1. flip coin random number of times --- //
			for (int flipNo = 0; flipNo < randomFlips; flipNo++){
				coin.swapFace();
				
				// --- 2. call GameEngineCallback.coinFlip(Coin.Face, GameEngine) while the coin is spinning ---
				for (GameEngineCallback gameEngineCallBack : this.callBacks)
					gameEngineCallBack.coinFlip(coin.getCurrentFace(), GameEngineImpl.this);
				
				// Pause flipping for specified delay time
				try {
					Thread.sleep(flipDelay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			/* --- 3. call GameEngineCallback.coinFlipOutcome(int, Coin.Face, GameEngine) 
					  when the coin has stopped spinning --- */
			for (GameEngineCallback gameEngineCallBack : this.callBacks)
				gameEngineCallBack.coinFlipOutcome(coinNo, coin.getCurrentFace(), GameEngineImpl.this);
			
			// Wait to flip next coin for specified delay time
			try {
				Thread.sleep(coinDelay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// --- 4. continue to perform coin flip with a new coin until all coins have been "flipped" ---
		}
		
		// --- 5. call GameEngine.calculateResult() ---
		this.calculateResult();
	}

	@Override
	public Collection<Player> getAllPlayers() {
		// convert map into arraylist (better for iterating through)
		return Collections.unmodifiableCollection(new ArrayList<Player>(
			this.players.values()));
	}

	@Override
	public Player getPlayer(String id) {
		// retrieve player from map, (map being faster than arraylist for retrieval)
		return this.players.get(id);
	}

	@Override
	public void placeBet(Player player, Face face, int bet) throws InsufficientFundsException {
		// places bet (doesn't effect creditPoints until game result does +/-)
		player.placeBet(face, bet);
	}

	@Override
	public void removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		// TODO Auto-generated method stub
		this.callBacks.remove(gameEngineCallback);
		
	}

	@Override
	public boolean removePlayer(Player player) {
		return players.remove(player.getPlayerId()) != null;
	}

	@Override
	public void setPlayerPoints(Player player, int totalPoints) {
		player.setPoints(totalPoints);
	}

}
