package au.edu.rmit.cpt222.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import au.edu.rmit.cpt222.viewGUI.GameControls;
import au.edu.rmit.cpt222.viewGUI.MainWindow;

public class MenuToolBarController implements ActionListener{

	private MainWindow mainWindow;
	private MainController mainController;

	public MenuToolBarController(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		this.mainController = mainWindow.getMainController();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// find out which button was clicked
		if (e.getActionCommand() == GameControls.NEXT_ROUND){
				mainController.takeTurn(mainWindow);
		}
	}

}
