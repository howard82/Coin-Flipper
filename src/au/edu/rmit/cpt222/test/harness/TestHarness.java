package au.edu.rmit.cpt222.test.harness;

import java.util.Collection;

import au.edu.rmit.cpt222.model.GameEngineCallbackImpl;
import au.edu.rmit.cpt222.model.GameEngineImpl;
import au.edu.rmit.cpt222.model.SimplePlayer;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.Coin;
import au.edu.rmit.cpt222.model.interfaces.Coin.Face;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

/**
 * Test Harness for SADI Assignment 1
 * 
 */
public class TestHarness {
	/**
	 * Sample Callback implementation to capture result callback calls for basic
	 * outcome/result testing.
	 */
	class ResultCallback implements GameEngineCallback {
		int countGameResult = 0;
		int countFlipOutcome = 0;

		public void clearResults() {
			this.countFlipOutcome = this.countGameResult = 0;
		}

		@Override
		public void coinFlip(Face coinFace, GameEngine engine) {
			// TODO Not used
		}

		@Override
		public void coinFlipOutcome(int numOfCoins, Face coinFace,
				GameEngine engine) {
			System.out.println("CALLBACK: coinFlipOutcome() called for test #"
					+ TestHarness.this.testNumber);
			this.countFlipOutcome++;
		}

		@Override
		public void gameResult(Player player, GameStatus status,
				GameEngine engine) {
			System.out
			.println("CALLBACK: gameResult() called for test #"
					+ TestHarness.this.testNumber + ", game outcome ="
					+ status);
			this.countGameResult++;
		}
	}

	public final static int DEFAULT_BET = 100;
	public final static int DEFAULT_FLIP_DELAY = 300;
	public final static int DEFAULT_COIN_DELAY = 500;
	public final static int DEFAULT_CREDIT_POINTS1 = 1000;
	public final static int DEFAULT_CREDIT_POINTS2 = 700;
	public final static int DEFAULT_CREDIT_POINTS3 = 500;

	public static void main(String args[]) {
		new TestHarness();
	}

	private float score = 0.0f;
	private int testNumber = 0;
	private GameEngineImpl gameEngine = new GameEngineImpl();
	private Player theGambler = new SimplePlayer("1", "The Gambler",
			DEFAULT_CREDIT_POINTS1);
	private Player theHustler = new SimplePlayer("2", "The Hustler",
			DEFAULT_CREDIT_POINTS2);
	private Player theCasual = new SimplePlayer("3", "The Casual",
			DEFAULT_CREDIT_POINTS3);
	private Collection<Player> players;
	private ResultCallback resultCallBack = new ResultCallback();

	public TestHarness() {

		// basic tests
		testAddPlayers();
		testRemovePlayer();
		testInvalidBet();
		testValidBet();
		testPreCondition();

		// game execution tests		
		this.gameEngine.addGameEngineCallback(this.resultCallBack);
		testFirstGame();
		testSecondGame();		
		testCallbackCalls();

		System.out
		.println("\nThe test harness score is: "
				+ this.score
				+ "/ 8.5 marks. NOTE: 1.5 additional marks are allocated based on callback console outputs.");
	}

	// helper method for handling unexpected exceptions
	private void handleException(Exception e, int testNumber, float partialScore) {
		System.out.println("*EXCEPTION* in test #" + testNumber + ", "
				+ e.getMessage());
		System.out.println("Partial Score: "
				+ String.format("(%.1f marks)", partialScore));
	}

	// reset one of the players for testing purposes
	private void resetGambler() {
		this.gameEngine.removePlayer(this.theGambler);
		this.theGambler = new SimplePlayer("1", "The Gambler", 1000);
		this.gameEngine.addPlayer(this.theGambler);
	}

	/**
	 * Test the addition of 3 players to the system. The expected size of the
	 * Collection is 3.
	 */
	private void testAddPlayers() {
		try {
			this.testNumber = 1;
			System.out.println("Test 01 - Adding Players (1.0 mark)");

			this.gameEngine.addPlayer(this.theGambler);
			this.gameEngine.addPlayer(this.theHustler);
			this.gameEngine.addPlayer(this.theCasual);

			if (this.gameEngine.getAllPlayers().size() == 3) {
				System.out.println("You successfully added three "
						+ "players to the game (1.0 mark)");
				this.score += 1.0;
			} else
				System.out.println("One or more of your players is "
						+ "incorrectly missing from the game (0.0 marks)");
		} catch (Exception e) {
			handleException(e, this.testNumber, 0);
		}
	}

	/**
	 * Test the number of the outcome callback calls.
	 */
	private void testCallbackCalls() {
		float localScore = 0.0f;
		try {
			this.testNumber = 8;
			System.out
			.println("\nTest 08 - Test Outcome Callback Calls (1.5 marks)");
			this.resultCallBack.clearResults();

			try {
				this.gameEngine.placeBet(this.theCasual, Coin.Face.tails,
						DEFAULT_BET);
			} catch (Exception e) {
				// should not be thrown here
				handleException(e, this.testNumber, 0);
			}
			this.gameEngine.flip(DEFAULT_FLIP_DELAY, DEFAULT_COIN_DELAY);

			System.out.println("Flip outcomes "
					+ this.resultCallBack.countFlipOutcome);

			System.out.println("Game outcomes "
					+ this.resultCallBack.countGameResult);
			// flip outcomes should be called only once per coin
			if (this.resultCallBack.countFlipOutcome == GameEngine.NUM_OF_COINS) {
				System.out.println("Flip outcomes called once per coin");

				this.score += 1.0;
			}
			if (this.resultCallBack.countGameResult == this.players.size()) {
				System.out.println("Game result called once per player");

				this.score += 0.5;
			} else
				System.out.println("Incorrect number of callbacks");
		} catch (Exception e) {
			handleException(e, this.testNumber, localScore);
		}
	}

	/**
	 * Execute game once and test the results.
	 */
	private void testFirstGame() {
		try {
			this.testNumber = 6;
			System.out
			.println("\nTest 06 - Test First Deal for The Gambler (1.5 marks)");

			testFlipImpl(this.theGambler, Coin.Face.heads, 1.5f);
		} catch (Exception e) {
			handleException(e, this.testNumber, 0);
		}
	}

	// helper method for testing game outcome
	private void testFlipImpl(Player player, Coin.Face selectedFace, float marks) {
		int currentPoints = player.getPoints();

		try {
			this.gameEngine.placeBet(player, selectedFace, DEFAULT_BET);
		} catch (InsufficientFundsException e) {
			// should not be thrown here
			handleException(e, this.testNumber, 0);
		}

		this.gameEngine.flip(DEFAULT_FLIP_DELAY, DEFAULT_COIN_DELAY);

		GameStatus result = player.getResult();

		// display updated player's info
		System.out.println(player);

		if (player.getPoints() == (currentPoints + DEFAULT_BET)
				|| player.getPoints() == (currentPoints - DEFAULT_BET)
				|| (result == GameStatus.DREW && player.getPoints() == currentPoints)) {
			System.out.println("You completed a successful deal with "
					+ player.getPlayerName()
					+ "'s point balances updated correctly (" + marks
					+ " marks)");

			this.score += marks;
		} else
			System.out.println(player.getPlayerName()
					+ "'s balance not updated correctly (0.0 marks)");
	}

	/**
	 * Test for a bet that exceeds a player's available points. The placeBet
	 * method should throw InsufficientFundsException.
	 */
	private void testInvalidBet() {
		// reset "gambler"
		this.resetGambler();

		try {
			this.testNumber = 3;
			System.out
			.println("\nTest 03 - Handling an Invalid Bet (1.0 mark)");

			this.theGambler.placeBet(Coin.Face.heads, 1100);
			// should not reach this line
			System.out.println(this.theGambler.getPlayerName()
					+ "'s invalid bet was not handled (0.0 marks)");
		} catch (InsufficientFundsException ife) {
			System.out.println("You successfully detected an "
					+ "invalid bet for insufficent credit scenario (1 mark)");
			this.score += 1.0;
		} catch (Exception e) {
			handleException(e, this.testNumber, 0);
		}
	}

	/**
	 * Tests that there is an assertion to capture illegal input whereby
	 * initialDelay > finalDelay.
	 */
	private void testPreCondition() {
		this.testNumber = 5;
		System.out.println("\nTest 05 - Test flip() Precondition (0.5 marks)");

		try {
			// place a bet (providing invalid input)
			this.theGambler.placeBet(null, -1);
			// play the game (providing invalid input)
			this.gameEngine.flip(DEFAULT_FLIP_DELAY, -1);

		} catch (AssertionError e) {
			System.out
			.println("Succesfully detected illegal input with assertion (0.5 marks)");
			this.score += 0.5;
			return;
		} catch (IllegalArgumentException e) {
			System.out
			.println("Succesfully detected illegal input with IllegalArgumentException (0.5 marks)");
			this.score += 0.5;
			return;
		} catch (Exception e) {
			System.out
			.println("TO DO: Caught Unknown Exception for illegal argument .. Marker to check code");

			return;
		}
		System.out.println("Did not capture illegal input (0.0 marks)");
	}

	/**
	 * Test the removal of 1 player from the system. The removePlayer() method
	 * should return false and the size of the player collection should be equal
	 * to 2.
	 */
	private void testRemovePlayer() {
		int numberOfPlayers = 0;

		try {
			this.testNumber = 2;
			System.out.println("\nTest 02 - Removing a Player (1.0 mark)");
			this.players = this.gameEngine.getAllPlayers();
			// display current players
			System.out.println("Initial players:");
			for (Player player : this.players) {
				System.out.println(player);
				numberOfPlayers++;
			}

			boolean isRemoved = this.gameEngine.removePlayer(this.theHustler);
			this.players = this.gameEngine.getAllPlayers();
			// display updated players
			System.out.println("Updated players:");
			for (Player player : this.players)
				System.out.println(player);

			if (isRemoved
					&& this.gameEngine.getAllPlayers().size() == --numberOfPlayers) {
				System.out.println("You successfully removed a player "
						+ "from the game (1.0 mark)");
				this.score += 1.0;
			} else
				System.out.println("Player was not removed from the game "
						+ "(0.0 marks)");
		} catch (Exception e) {
			handleException(e, this.testNumber, 0);
		}
	}

	/**
	 * Execute game a second time and test the results.
	 */
	private void testSecondGame() {
		try {
			this.testNumber = 7;
			System.out
			.println("\nTest 07 - Test Second Deal for the Casual (1.5 marks)");

			testFlipImpl(this.theCasual, Coin.Face.tails, 1.5f);
		} catch (Exception e) {
			handleException(e, this.testNumber, 0);
		}
	}

	/**
	 * Test for a valid bet. Checks if the game engine's placeBet() method
	 * forwards the call to the appropriate player.
	 */
	private void testValidBet() {
		try {
			this.testNumber = 4;
			System.out.println("\nTest 04 - Handling a Valid Bet (0.5 marks)");

			this.gameEngine.placeBet(this.theCasual, Coin.Face.tails,
					DEFAULT_BET);

			if (this.theCasual.getBet() == DEFAULT_BET
					&& this.theCasual.getFacePick() == Coin.Face.tails) {
				System.out.println(this.theCasual.getPlayerName()
						+ " successfully made a bet (0.5 marks)");
				this.score += 0.5;
			} else
				System.out.println(this.theCasual.getPlayerName()
						+ "'s getBet() was incorrect (0.0 marks)");
		} catch (InsufficientFundsException ife) {
			System.out.println(this.theCasual.getPlayerName()
					+ "'s bet throws unnecessary exception (0.0 marks)");
		} catch (Exception e) {
			handleException(e, this.testNumber, 0);
		}
	}
}