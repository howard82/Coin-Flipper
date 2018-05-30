package au.edu.rmit.cpt222.viewGUI;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
import au.edu.rmit.cpt222.controller.GameToolBarController;

@SuppressWarnings("serial")
public class GameControls extends JToolBar {
	public static final String NEXT_ROUND = "Next Round";
	
	private JButton nextRoundButton;
	
	// Controllers
	private GameToolBarController gameToolBarController;
	private GamePanel gamePanel;
	
	public GameControls(GamePanel gamePanel){
		// populate instance variables
		this.gamePanel = gamePanel;
		gameToolBarController = new GameToolBarController(this);
		
		// style Toolbar
		this.setLayout(new GridLayout());
		this.setFloatable(false);
		this.setPreferredSize(new Dimension(0,50));

		// create button element
		nextRoundButton = new JButton("Play Next Round");
		nextRoundButton.setActionCommand(NEXT_ROUND);
		nextRoundButton.setPreferredSize(new Dimension(50, 50));
		nextRoundButton.addActionListener(gameToolBarController);
		
		// add elements to Toolbar
		this.add(nextRoundButton);	
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public GameToolBarController getGameToolBarController() {
		return gameToolBarController;
	}

	public JButton getNextRoundButton() {
		return nextRoundButton;
	}
	
}
