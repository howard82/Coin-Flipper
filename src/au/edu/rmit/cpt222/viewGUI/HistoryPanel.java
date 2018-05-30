package au.edu.rmit.cpt222.viewGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class HistoryPanel extends JPanel {
	private MainWindow mainWindow;
	private JLabel titleLabel;
	private JTextArea gameHistory;
	
	public HistoryPanel(MainWindow mainWindow){
		this.mainWindow = mainWindow;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(200,0));
		
		// make panel components
		titleLabel = new  JLabel("History", JLabel.CENTER);
		titleLabel.setPreferredSize(new Dimension(100, 50));
		titleLabel.setFont(MainWindow.titleFont);
		titleLabel.setOpaque(true);
		
		gameHistory = new JTextArea();
		gameHistory.setLineWrap(true);
		gameHistory.setEditable(false);
		JScrollPane scroll = new JScrollPane(gameHistory);
		
		// add components to the panel
		this.add(titleLabel, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);
	}
	
	public JTextArea getGameHistory() {
		return gameHistory;		
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}
}
