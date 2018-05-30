package au.edu.rmit.cpt222.viewGUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

import au.edu.rmit.cpt222.controller.NewPlayerController;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class NewPlayerPanel extends JFrame {
	public static final String NEW_PLAYER = "Create New Player";
	private JTextField id;
	private JTextField name;
	private JSpinner credits;
	private GameEngine model;
	
	public NewPlayerPanel(GameEngine model){
		// set container formatting
		this.setTitle("Create New Player");
		this.model = model;
		this.setSize(300, 200);
		this.setLayout(new GridLayout());
	    this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		// centre on screen
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	    
	    // create components
		JPanel panel = new JPanel();
		Dimension d = new Dimension(120, 30);
		
		JLabel idLabel = new JLabel("Enter player ID");
		idLabel.setPreferredSize(d);
		id = new JTextField("1");
		id.setPreferredSize(d);
		panel.add(idLabel);
		panel.add(id);
		
		JLabel nameLabel = new JLabel("Enter player name");
		nameLabel.setPreferredSize(d);
		name = new JTextField("Player 1");
		name.setPreferredSize(d);
		panel.add(nameLabel);
		panel.add(name);

		JLabel pointsLabel = new JLabel("Starting Points");
		pointsLabel.setPreferredSize(d);
		SpinnerNumberModel sModel = new SpinnerNumberModel(
			100, 100, Integer.MAX_VALUE, 100);
		credits = new JSpinner(sModel);
		credits.setEditor(new JSpinner.DefaultEditor(credits));
		credits.setPreferredSize(d);
		panel.add(pointsLabel);
		panel.add(credits);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new NewPlayerController(this));
		okButton.setActionCommand(NEW_PLAYER);
		panel.add(okButton);

		this.add(panel);
		this.setVisible(true);
	}

	public GameEngine getModel() {
		return model;
	}

	public String getId() {
		return id.getText();
	}

	public String getName() {
		return name.getText();
	}

	public int getCredits() {
		try {
			credits.commitEdit();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) credits.getValue();
	}
}
