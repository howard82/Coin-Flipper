package au.edu.rmit.cpt222.driver;

import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import au.edu.rmit.cpt222.model.GameEngineImpl;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.viewGUI.NewPlayerPanel;;

public class Ass1Driver {

	final int COIN_DELAY = 1000;
	final int FLIP_DELAY = 1000;
	
	public static void main(String[] args) {
		
		// create spinner for dialog with minimum as number of coins from GameEngine Model and even increments
		SpinnerNumberModel sModel = new SpinnerNumberModel(
			GameEngine.NUM_OF_COINS, GameEngine.NUM_OF_COINS, Integer.MAX_VALUE, 2);
		JSpinner spinner = new JSpinner(sModel);
		spinner.setEditor(new JSpinner.DefaultEditor(spinner));		
		
		// use input dialogue to ask the user to specify coin number
		int coinNumber = JOptionPane.showOptionDialog(null, spinner, 
				"Choose quantity of game coins to use", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		
		// if user doesn't hit cancel proceed
		if (coinNumber == JOptionPane.OK_OPTION) {
			// use number specified by user to initialise the gameEngine
			GameEngine model = new GameEngineImpl((int)spinner.getValue());
			
			SwingUtilities.invokeLater(new Runnable(){

				@Override
				public void run() {
//					 initialise user interface
					new NewPlayerPanel(model);
				}
				});
			}
		}	
	}
