package au.edu.rmit.cpt222.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import au.edu.rmit.cpt222.viewGUI.GameControls;
import au.edu.rmit.cpt222.viewGUI.MainWindow;

public class GameToolBarController implements ActionListener { //extends SwingWorker<Void, Integer> (maybe need for assignment 2)
	
	private GameControls gameToolBar;
	private MainWindow mainWindow;
	private MainController mainController;

	public GameToolBarController(GameControls gameToolBar) {
		this.gameToolBar = gameToolBar;
		this.mainWindow = gameToolBar.getGamePanel().getMainWindow();
		this.mainController = mainWindow.getMainController();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// find out which button was clicked
		if (e.getActionCommand() == GameControls.NEXT_ROUND){
				mainController.takeTurn(mainWindow);			
			
//			I've left this here because it seems like I'll need to do this in some form in Assignment 2
			//this.execute();
			
		}
	}

	public GameControls getGameToolBar() {
		return gameToolBar;
	}
	
//	I've left this here because it seems like I'll need to do this in some form in Assignment 2
//	@Override
//	protected Void doInBackground() throws Exception {
//		model.flip(500, 1000);
//		return null;
//	}
}
