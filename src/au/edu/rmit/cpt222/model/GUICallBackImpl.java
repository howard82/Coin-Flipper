package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.controller.MainController;
import au.edu.rmit.cpt222.model.interfaces.Coin;
import au.edu.rmit.cpt222.model.interfaces.Coin.Face;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;

public class GUICallBackImpl implements GameEngineCallback {
	private MainController mainController;
	
	public GUICallBackImpl(MainController mainController){
		this.mainController = mainController;
	}
	
	@Override
	public void gameResult(Player player, GameStatus result, GameEngine engine) {
		mainController.updateResult(player, result, engine);
	}

	@Override
	public void coinFlip(Coin.Face coinFace, GameEngine engine) {
		mainController.coinFlip(coinFace, engine);
	}

	@Override
	public void coinFlipOutcome(int coinNumber, Face coinFace, GameEngine engine) {
		mainController.coinFlipOutcome(coinNumber, coinFace, engine);
	}
}
