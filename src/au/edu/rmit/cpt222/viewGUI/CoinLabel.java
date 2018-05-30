package au.edu.rmit.cpt222.viewGUI;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import au.edu.rmit.cpt222.model.interfaces.Coin.Face;

@SuppressWarnings("serial")
public class CoinLabel extends JLabel {
	ImageIcon headsImage = new ImageIcon("images/heads.png");
	ImageIcon tailsImage = new ImageIcon("images/tails.png");
	GamePanel gamePanel;
	
	public CoinLabel(GamePanel gamePanel){
		super();
		this.gamePanel = gamePanel;
		this.setForeground(Color.WHITE);
		this.setAlignmentX(LEFT);
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(200,200));
	}

	public void setImageIcon(Face face) {
		// use selected Face to determine which coin image to display
		if (face == Face.heads)
			this.setIcon(headsImage);
		else if (face == Face.tails)
			this.setIcon(tailsImage);
		else
			this.setIcon(null);
	}
}
