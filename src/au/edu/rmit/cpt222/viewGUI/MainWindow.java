package au.edu.rmit.cpt222.viewGUI;

import au.edu.rmit.cpt222.controller.MainController;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	public static Font titleFont = new Font("Serif", Font.BOLD, 30);
	
	// model related variables
	private GameEngine model;
	public Player player;
	
	// declare all sub-views
	private GamePanel gamePanel;
	private MainController mainController;
	private GameMenuBar gameMenuPanel;
	private HistoryPanel historyPanel;
	private PlayerPanel playerPanel;

	// Tge purpose of this class is to display menu items for main system functions
	public MainWindow (GameEngine model, Player player){
		super("CPT222 Ass 1: Heads/Tails Game");
		this.model = model;
		this.mainController = new MainController(this);
		this.player = player;
		initView();
	}
	
	private void initView(){			
		// define window format and place in the centre of the screen
		this.setBounds(200, 50, 500, 400);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setFont(titleFont);
		
		// initiate subviews
		this.gamePanel = new GamePanel(this);
		this.gameMenuPanel = new GameMenuBar(this);
		this.historyPanel = new HistoryPanel(this);
		this.playerPanel = new PlayerPanel(this);
		
		// add sub-views to the mainwindow
		this.add(gamePanel, BorderLayout.CENTER);
		this.add(historyPanel, BorderLayout.EAST);
		this.add(playerPanel, BorderLayout.SOUTH);
		this.setJMenuBar(this.gameMenuPanel);
	}

	public GameEngine getModel() {
		return model;
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public GameMenuBar getGameMenuPanel() {
		return gameMenuPanel;
	}

	public HistoryPanel getHistoryPanel() {
		return historyPanel;
	}

	public MainController getMainController() {
		return mainController;
	}

	public PlayerPanel getPlayerPanel() {
		return playerPanel;
		
	}
}
