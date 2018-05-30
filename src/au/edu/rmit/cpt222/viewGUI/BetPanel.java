package au.edu.rmit.cpt222.viewGUI;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import au.edu.rmit.cpt222.controller.BetPanelController;
import au.edu.rmit.cpt222.model.interfaces.Player;

@SuppressWarnings("serial")
public class BetPanel extends JFrame{
	JRadioButton[] chooseFace = new JRadioButton[2];
	public JSpinner spinner;
	public Player player;
	private MainWindow mainWindow;
	public static final String PLACE_BET = "Place Bet";
	
	public BetPanel(MainWindow mainWindow){
		this.mainWindow = mainWindow;
		this.player = mainWindow.player;
		this.setBounds(200, 50, 260, 150);
		
		// put window in centre of screen
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	    
		BetPanelController listener = new BetPanelController(this);
		
		JPanel panel = new JPanel();
	
		JLabel facePromptLabel = new JLabel("Choose a coin face:");
		chooseFace[0] = new JRadioButton("Heads");
		chooseFace[1] = new JRadioButton("Tails");
		chooseFace[0].setSelected(true);
		panel.add(facePromptLabel);
		
		// adds heads and tails options to button group
		ButtonGroup group = new ButtonGroup();
		for (int i = 0; i < chooseFace.length; i++)
		{
			group.add(chooseFace[i]);
			panel.add(chooseFace[i]);
		}
		
		JLabel betPromptLabel = new JLabel("Select your bet amount:");
		SpinnerNumberModel sModel = new SpinnerNumberModel(0, 0, player.getPoints(), 10);
		spinner = new JSpinner(sModel);
		
		// set spinner to last bet value or if insufficient funds to value of credit 
		if (player.getPoints() >= player.getBet())
			spinner.setValue(player.getBet());
		else
			spinner.setValue(player.getPoints());
		
		spinner.setEditor(new JSpinner.DefaultEditor(spinner));
		spinner.setPreferredSize(new Dimension(60,20));
		panel.add(betPromptLabel);
		panel.add(spinner);
		
		JButton okButton = new JButton("OK");
		okButton.setActionCommand(PLACE_BET);
		okButton.addActionListener(listener);
		
		this.add(panel);
		panel.add(okButton);
		this.setVisible(true);
		
	}

	public MainWindow getMainWindow(){
		return mainWindow;
	}
	public JRadioButton[] getChooseFace() {
		return chooseFace;
	}
}
