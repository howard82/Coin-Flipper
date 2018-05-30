package au.edu.rmit.cpt222.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import au.edu.rmit.cpt222.viewGUI.BetPanel;

public class BetPanelController implements ActionListener {

	private BetPanel betPanel;
	private MainController mainController;
	
	public BetPanelController(BetPanel betPanel){
		this.betPanel = betPanel;
		this.mainController = betPanel.getMainWindow().getMainController();
	}
	@Override
	public void actionPerformed(ActionEvent click) {
		// modify players bet amount and facepick
		if (click.getActionCommand() == BetPanel.PLACE_BET){
			mainController.placeBet(betPanel);
		}
	}

}
