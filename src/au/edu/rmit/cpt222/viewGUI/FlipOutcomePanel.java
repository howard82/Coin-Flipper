package au.edu.rmit.cpt222.viewGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FlipOutcomePanel extends JPanel {

	GamePanel gamePanel;
	JLabel headsLabel;
	JLabel tailsLabel;
	JLabel headsCountLabel;
	JLabel tailsCountLabel;
	
	public FlipOutcomePanel(GamePanel gamePanel){
		this.gamePanel = gamePanel;
		this.setLayout(new GridLayout());
		
		this.headsLabel = new JLabel("Heads", JLabel.CENTER);
		this.tailsLabel = new JLabel("Tails", JLabel.CENTER);
		this.headsCountLabel = new JLabel("0", JLabel.CENTER);
		this.tailsCountLabel = new JLabel("0", JLabel.CENTER);
		headsCountLabel.setPreferredSize(new Dimension(100,50));
		headsCountLabel.setOpaque(true);
		tailsCountLabel.setOpaque(true);
		
		this.add(headsLabel);
		this.add(headsCountLabel);
		this.add(tailsLabel);
		this.add(tailsCountLabel);
	}

	public void setHeadsCount(int headsCount) {
		this.headsCountLabel.setText(Integer.toString(headsCount));
	}

	public void setTailsCount(int tailsCount) {
		this.tailsCountLabel.setText(Integer.toString(tailsCount));
	}

	public JLabel getHeadsCountLabel() {
		return headsCountLabel;
	}

	public JLabel getTailsCountLabel() {
		return tailsCountLabel;
	}
	
	public void setCountColors(int headsCount, int tailsCount){
		// sets the label for the flip outcome count to indicate which is in the lead, black for even
		if (headsCount > tailsCount){
			this.getHeadsCountLabel().setForeground(Color.BLUE);
			this.getTailsCountLabel().setForeground(Color.RED);
		} else if (headsCount < tailsCount){
			this.getHeadsCountLabel().setForeground(Color.RED);
			this.getTailsCountLabel().setForeground(Color.BLUE);
		} else{
			this.getHeadsCountLabel().setForeground(Color.BLACK);
			this.getTailsCountLabel().setForeground(Color.BLACK);
		}
	}
}
