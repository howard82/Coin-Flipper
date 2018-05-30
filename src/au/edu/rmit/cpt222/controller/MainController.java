package au.edu.rmit.cpt222.controller;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import au.edu.rmit.cpt222.model.GUICallBackImpl;
import au.edu.rmit.cpt222.model.GameEngineCallbackImpl;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.Coin;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.model.interfaces.Coin.Face;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.viewGUI.BetPanel;
import au.edu.rmit.cpt222.viewGUI.MainWindow;

public class MainController {
	private GameEngine model;
	private int tailsCount = 0;
	private int headsCount = 0;
	private MainWindow mainWindow;
	
	public MainController(MainWindow mainWindow){
		this.mainWindow = mainWindow;
		this.model = mainWindow.getModel();
		model.addGameEngineCallback(new GUICallBackImpl(this));
		model.addGameEngineCallback(new GameEngineCallbackImpl());
	}
	public void takeTurn (MainWindow mainWindow){
		// allow betting if players still in the game
		if (!model.getAllPlayers().isEmpty())
			new BetPanel(mainWindow);
	}
	
	public void placeBet(BetPanel betPanel) {
		
		// determine the coin face chosen by the player
		Coin.Face face = betPanel.player.getFacePick();
		if (betPanel.getChooseFace()[0].isSelected()){
			face = Coin.Face.heads;
		}
		if (betPanel.getChooseFace()[1].isSelected()){
			face = Coin.Face.tails;
		}
		
		// place player bet
		try {
			betPanel.player.placeBet(face, (int) betPanel.spinner.getValue());
			betPanel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			betPanel.dispose();
			
			// Update the player bet and face selection in GUI
			mainWindow.getPlayerPanel().setPlayerBet((int) betPanel.spinner.getValue());
			mainWindow.getPlayerPanel().setPlayerFace(face);
			new Thread()
			{
		       @Override
		       public void run()
		       {
		    	   // do a game round
		    	   model.flip(500, 1000);
		       }
			}.start();
			
			} catch (InsufficientFundsException e) {
				new JDialog(betPanel, "Insufficient Funds");
		}
	}
	
	public void updateResult(Player player, GameStatus result, GameEngine engine){
	
		// clear any green backgrounds from result count labels
		mainWindow.getGamePanel().getFlipOutcomePanel().getHeadsCountLabel().setBackground(null);
		mainWindow.getGamePanel().getFlipOutcomePanel().getTailsCountLabel().setBackground(null);
		
		// Display game outcome prompt
		String message = "Game was a draw";
		if (headsCount > tailsCount){
			message = "Heads Wins";
		} else if (headsCount < tailsCount){
			message = "Tails Wins";
		}
		JOptionPane.showMessageDialog(mainWindow, message);

		// update game history with latest round results
		mainWindow.getHistoryPanel().getGameHistory().append(
			player.getPlayerName() + " bet $" + player.getBet() + " on " + player.getFacePick() + ": " + result + "\n");
		// update player points
		mainWindow.getPlayerPanel().setPlayerScore(player.getPoints());
		
		// reset count, make text of heads/tails label black / remove coin image
		headsCount = 0;
		tailsCount = 0;
		mainWindow.getGamePanel().getFlipOutcomePanel().setHeadsCount(headsCount);
		mainWindow.getGamePanel().getFlipOutcomePanel().setTailsCount(tailsCount);
		mainWindow.getGamePanel().getFlipOutcomePanel().setCountColors(headsCount, tailsCount);
		mainWindow.getGamePanel().getCoinLabel().setImageIcon(null);
		
		// if player has no points display "game over" prompt 
		if (player.getPoints() == 0){
			JOptionPane.showMessageDialog(null, "Game over for " + ": '" + player.getPlayerName() + "'. No more points left");
			model.removePlayer(player);
			mainWindow.getHistoryPanel().getGameHistory().append(
				"Game over for " + player.getPlayerName() + "\n");
		}
	}

	public void coinFlip(Coin.Face coinFace, GameEngine engine) {
		// Display coin image in gamePanel
		mainWindow.getGamePanel().getCoinLabel().setImageIcon(coinFace);
		
		// Reset background colour of the head/tails count labels
		mainWindow.getGamePanel().getFlipOutcomePanel().getHeadsCountLabel().setBackground(null);
		mainWindow.getGamePanel().getFlipOutcomePanel().getTailsCountLabel().setBackground(null);
	}

	public void coinFlipOutcome(int coinNumber, Face coinFace, GameEngine engine) {
		// set the winning coins count label background to green and increase the count by 1
		if (coinFace == Face.heads){
			mainWindow.getGamePanel().getFlipOutcomePanel().getHeadsCountLabel().setBackground(Color.GREEN);
			headsCount++;
			mainWindow.getGamePanel().getFlipOutcomePanel().setHeadsCount(headsCount);
		}
		else{
			mainWindow.getGamePanel().getFlipOutcomePanel().getTailsCountLabel().setBackground(Color.GREEN);
			tailsCount++;
			mainWindow.getGamePanel().getFlipOutcomePanel().setTailsCount(tailsCount);
		}
		
		// set the colours of text to blue or red for winning or losing or black for tie
		mainWindow.getGamePanel().getFlipOutcomePanel().setCountColors(headsCount, tailsCount);
	}
}
