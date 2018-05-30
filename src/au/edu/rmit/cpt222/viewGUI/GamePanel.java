package au.edu.rmit.cpt222.viewGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	MainWindow mainWindow;
	// According to Collaborate chat can be JPanel or JToolBar. Purpose display buttons for performing main system functions
	private GameControls gameControls;
	private CoinLabel coinLabel;
	private FlipOutcomePanel flipOutcomePanel;
	
	public GamePanel(MainWindow mainWindow){
		//set instance variables
		this.mainWindow = mainWindow;
		this.gameControls = new GameControls(this);
		this.coinLabel = new CoinLabel(this);
		this.flipOutcomePanel = new FlipOutcomePanel(this);
		
		// format GamePanel
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		
		this.add(flipOutcomePanel, BorderLayout.NORTH);
		this.add(coinLabel, BorderLayout.CENTER);
		this.add(gameControls, BorderLayout.SOUTH);
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public CoinLabel getCoinLabel() {
		return coinLabel;
	}

	public void setCoinLabel(CoinLabel coinLabel) {
		this.coinLabel = coinLabel;
	}

	public GameControls getGameControls() {
		return gameControls;
	}

	public FlipOutcomePanel getFlipOutcomePanel() {
		return flipOutcomePanel;
	}
	
	
}
