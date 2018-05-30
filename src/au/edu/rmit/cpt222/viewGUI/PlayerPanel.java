package au.edu.rmit.cpt222.viewGUI;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import au.edu.rmit.cpt222.model.interfaces.Coin.Face;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
	public static String HEADS = "Heads";
	public static String TAILS = "Tails";
	
	private JLabel playerName;
	private JLabel playerBet;
	private JLabel playerFace;
	private JLabel playerPoints;
	
	public PlayerPanel(MainWindow mainWindow){
		playerName = new JLabel(mainWindow.player.getPlayerName(), JLabel.CENTER);
		playerBet = new JLabel("",JLabel.CENTER);
		setPlayerBet(mainWindow.player.getBet());
		playerPoints = new JLabel("",JLabel.CENTER);
		setPlayerScore(mainWindow.player.getPoints());
		playerFace = new JLabel("",JLabel.CENTER);
		setPlayerFace (mainWindow.player.getFacePick());
		
		this.setLayout(new GridLayout(2, 3));
		this.add(new JLabel("Player Name: ", JLabel.CENTER));
		this.add(new JLabel("Player Points: ", JLabel.CENTER));
		this.add(new JLabel("Current Bet: ", JLabel.CENTER));
		this.add(new JLabel("Current Face: ", JLabel.CENTER));
		
		this.add(playerName);
		this.add(playerPoints);
		this.add(playerBet);
		this.add(playerFace);
	}

	public void setPlayerBet(int playerBet) {
		this.playerBet.setText(Integer.toString(playerBet));
	}

	public void setPlayerFace(Face playerFace) {
		if (playerFace == Face.heads)
			this.playerFace.setText(HEADS);
		if (playerFace == Face.tails)
			this.playerFace.setText(TAILS);
	}

	public void setPlayerScore(int playerScore) {
		this.playerPoints.setText(Integer.toString(playerScore));
	}
	
	
}
