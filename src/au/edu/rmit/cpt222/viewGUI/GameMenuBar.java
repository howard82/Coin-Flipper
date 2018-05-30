package au.edu.rmit.cpt222.viewGUI;
import javax.swing.*;

import au.edu.rmit.cpt222.controller.MenuToolBarController;

@SuppressWarnings("serial")
public class GameMenuBar extends JMenuBar {
	public static final String NEXT_ROUND = "Next Round";
	private JMenu menu;
	private JMenuItem nextRoundItem;
	private MainWindow mainWindow;
	private MenuToolBarController menuController;
	
	public GameMenuBar(MainWindow mainWindow){
		// instantiate instance variables
		this.mainWindow = mainWindow;
		this.menuController = new MenuToolBarController(mainWindow);
		this.menu = new JMenu("Game Menu");
		nextRoundItem = new JMenuItem("Play Next Round");
		
		// add listeners
		nextRoundItem.setActionCommand(NEXT_ROUND);
//		nextRoundItem.setPreferredSize(new Dimension(50, 50));
		nextRoundItem.addActionListener(menuController);
		this.add(menu);
		
//		this.setBackground(Color.GREEN);
		menu.add(nextRoundItem);
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}
	
	
}
