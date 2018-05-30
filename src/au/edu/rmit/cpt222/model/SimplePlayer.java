package au.edu.rmit.cpt222.model;

import java.io.Serializable;

import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.*;
import au.edu.rmit.cpt222.model.interfaces.Coin.Face;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
 	 	

@SuppressWarnings("serial")
public class SimplePlayer implements Player, Serializable {
	
	String playerId;
	String playerName;
	int creditPoints;
	int bet = 0;
	Face facePick;// = Coin.Face;
	GameStatus gameResult;
	
	public SimplePlayer(String playerId, String name, int creditPoints){
		this.playerId = playerId;
		this.playerName = name;
		this.creditPoints = creditPoints;
	}

	@Override
	public int getBet() {
		// TODO Auto-generated method stub
		return bet;
	}

	@Override
	public Face getFacePick() {
		// TODO Auto-generated method stub
		return facePick;
	}

	@Override
	public String getPlayerId() {
		// TODO Auto-generated method stub
		return playerId;
	}

	@Override
	public String getPlayerName() {
		// TODO Auto-generated method stub
		return playerName;
	}

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return creditPoints;
	}

	@Override
	public GameStatus getResult() {
		
		return gameResult;
	}

	@Override
	public void placeBet(Face facePick, int bet) throws InsufficientFundsException, IllegalArgumentException {
		// if the bet is less than zero throw assert exception
		
		assert (bet > 0);
		
		// check player can afford bet, if not exception for insufficient funds
		if (facePick == null)
			throw new IllegalArgumentException();
		
		// check player can afford bet, if not exception for insufficient funds
		if (bet > creditPoints)
			throw new InsufficientFundsException();
				
		this.bet = bet;
		this.facePick = facePick;
	}

	@Override
	public void setPlayerName(String playerName) {
		// TODO Auto-generated method stub
		this.playerName = playerName;
	}

	@Override
	public void setPoints(int points) {
		this.creditPoints = points;
		
	}

	@Override
	public void setResult(GameStatus status) {
		this.gameResult = status;
	}
	
	@Override
	public String toString(){
		return playerId + " " + playerName;
	}
}
