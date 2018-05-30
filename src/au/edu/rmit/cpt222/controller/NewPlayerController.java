package au.edu.rmit.cpt222.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import au.edu.rmit.cpt222.model.SimplePlayer;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.viewGUI.MainWindow;
import au.edu.rmit.cpt222.viewGUI.NewPlayerPanel;

public class NewPlayerController implements ActionListener {

	NewPlayerPanel newPlayerPanel;
	private GameEngine model;
	
	public NewPlayerController(NewPlayerPanel newPlayerPanel){
		this.newPlayerPanel = newPlayerPanel;
		this.model = newPlayerPanel.getModel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// new player option
		if (e.getActionCommand() == NewPlayerPanel.NEW_PLAYER){
			model.addPlayer(new SimplePlayer(newPlayerPanel.getId(), newPlayerPanel.getName(), newPlayerPanel.getCredits()));
			newPlayerPanel.dispose();
			for (Player player : model.getAllPlayers()){
				MainWindow mainWindow = new MainWindow(model, player);
				mainWindow.setVisible(true);
			}
		}
	}
}
