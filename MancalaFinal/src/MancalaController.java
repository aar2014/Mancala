import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This the second board style for the Mancala. Group Project: Mancala. CS-151.
 * 
 * Group Project: Mancala. CS-151.
 * 
 * @author Team Alpha: Nirav Patel, Shubhangi Rankhode, Vaishali Shah
 */
public class MancalaController {
	private static final int PITS_PER_PLAYER = 6;

	private MancalaView mancalaView;
	
	private GamePlayer player1;
	
	private GamePlayer player2;
	
	private PreviousMove previousMove;
	
	private boolean undoEnable;
	
	private ChangeListener undoListener;
	

/**
 * Controller initializes players, view of the Mancala game
 */
	public MancalaController() {
		initializeGamePlayers();
		undoEnable = true;
		int numOfStones = selectNumberOfStones();
		initializeMancalaPits(numOfStones);
		mancalaView = new MancalaView(this, numOfStones);
		changeStyles();

		// set previous move as start of the game
		previousMove = new PreviousMove();
	}
	
	/**
	 * Asks player for initial number of stones in a pit
	 * @return enum with number of stones
	 */
	private int selectNumberOfStones() {
		int pos = JOptionPane.showOptionDialog(null, "Select Number of Stones",
				"Number of Stones", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, StoneS.values(), StoneS.THREE);
		return StoneS.get(pos);

	}

	/**
	 * Take user input for style selection and assign selected style to board
	 */
	public void changeStyles() {
		int style = JOptionPane.showOptionDialog(null, "Select a Style",
				"Style Selection", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, new String[] { "Style1",
						"Style2" }, "Style1");

		StylePanel sp;
		if (style == 0) {
			sp = new StylePanel2();
		} else if (style == 1) {
			sp = new StylePanel1();

		} else {
			throw new IllegalArgumentException(
					"Selected style not implemented!");
		}

		mancalaView.selectStyle(sp);

	}

	/**
	 * enum having number of stones
	 *
	 */
	public static enum StoneS {
		THREE, FOUR;
		public static int get(int pos) {
			if (pos == 0) {
				return 3;
			} else if (pos == 1) {
				return 4;
			} else {
				throw new IllegalArgumentException("Invalid pos: " + pos);
			}
		}
	}

	/**
	 * Initialize game players at the start of the game
	 */
	private void initializeGamePlayers() {
		player1 = new GamePlayer(this, "Player A");
		player1.setPlayerTurn(true);
		player2 = new GamePlayer(this, "Player B");
	}

	/**
	 * After taking number of stones as an input from player, initialize
	 * each mancala pit with respective number of stones
	 * @param numOfStones
	 * 					 selected by player
	 */
	private void initializeMancalaPits(int numOfStones) {

		SmallPit[] gamePlayer1Pit = new SmallPit[6];
		SmallPit[] gamePlayer2Pit = new SmallPit[6];

		initializeSmallPits(numOfStones, gamePlayer1Pit, gamePlayer2Pit);

		// initializing Last Pit for Player1 and Player2
		EndPit gamePlayer1LastPit = new EndPit(player1);
		EndPit gamePlayer2LastPit = new EndPit(player2);
		gamePlayer1LastPit.setNumberOfStones(numOfStones);
		gamePlayer2LastPit.setNumberOfStones(numOfStones);

		setInversePits(gamePlayer1Pit, gamePlayer2Pit, gamePlayer1LastPit,
				gamePlayer2LastPit);

		// Initializes large pit for player1 and player2
		LargePit gamePlayer1LargePit = new LargePit(player1);
		LargePit gamePlayer2LargePit = new LargePit(player2);
		gamePlayer1LargePit.setNumberOfStones(0);
		gamePlayer2LargePit.setNumberOfStones(0);
		gamePlayer1LargePit.setNextPit(gamePlayer2Pit[0]);
		gamePlayer2LargePit.setNextPit(gamePlayer1Pit[0]);

		setNextPit(gamePlayer1Pit, gamePlayer2Pit, gamePlayer1LargePit,
				gamePlayer2LargePit);

		setSmallLargePits(gamePlayer1Pit, gamePlayer2Pit, gamePlayer1LargePit,
				gamePlayer2LargePit);
	}

	/**
	 * Initialize small pits for each player.
	 * 
	 * @param numOfStones
	 *            number of stones in pits.
	 * @param gamePlayer1Pit
	 *            player1 small pit.
	 * @param gamePlayer2Pit
	 *            player2 small pit.
	 */
	private void initializeSmallPits(int numOfStones,
			SmallPit[] gamePlayer1Pit, SmallPit[] gamePlayer2Pit) {
		for (int i = 0; i < PITS_PER_PLAYER - 1; i++) {
			gamePlayer1Pit[i] = new SmallPit(player1);
			gamePlayer1Pit[i].setNumberOfStones(numOfStones);
		}

		for (int i = 0; i < PITS_PER_PLAYER - 1; i++) {
			gamePlayer2Pit[i] = new SmallPit(player2);
			gamePlayer2Pit[i].setNumberOfStones(numOfStones);
		}
	}

	/**
	 * Set the inverse pits for the player1 and player2.
	 * 
	 * @param gamePlayer1Pit
	 *            player1 pit.
	 * @param gamePlayer2Pit
	 *            player2 pit.
	 * @param gamePlayer1LastPit
	 *            player1 last pit.
	 * @param gamePlayer2LastPit
	 *            player2 last pit.
	 */
	private void setInversePits(SmallPit[] gamePlayer1Pit,
			SmallPit[] gamePlayer2Pit, EndPit gamePlayer1LastPit,
			EndPit gamePlayer2LastPit) {

		gamePlayer1LastPit.setOtherPlayersPit(gamePlayer2Pit[0]);
		gamePlayer2LastPit.setOtherPlayersPit(gamePlayer1Pit[0]);

		gamePlayer1Pit[PITS_PER_PLAYER - 1] = gamePlayer1LastPit;
		gamePlayer2Pit[PITS_PER_PLAYER - 1] = gamePlayer2LastPit;

		for (int i = 0; i < PITS_PER_PLAYER; i++) {
			gamePlayer1Pit[i].setOtherPlayersSmallPit(gamePlayer2Pit[PITS_PER_PLAYER
					- 1 - i]);
			gamePlayer2Pit[i].setOtherPlayersSmallPit(gamePlayer1Pit[PITS_PER_PLAYER
					- 1 - i]);
		}
	}

	/**
	 * Set the next pit for the players.
	 * 
	 * @param gamePlayer1Pit
	 *            player1 pit.
	 * @param gamePlayer2Pit
	 *            player2 pit.
	 * @param gamePlayer1LargePit
	 *            player1 large pit.
	 * @param gamePlayer2LargePit
	 *            player2 large pit.
	 */
	private void setNextPit(SmallPit[] gamePlayer1Pit,
			SmallPit[] gamePlayer2Pit, LargePit gamePlayer1LargePit,
			LargePit gamePlayer2LargePit) {
		for (int i = 0; i < PITS_PER_PLAYER; i++) {
			if (i < PITS_PER_PLAYER - 1) {
				gamePlayer1Pit[i].setNextPit(gamePlayer1Pit[i + 1]);
			} else {
				gamePlayer1Pit[i].setNextPit(gamePlayer1LargePit);
			}
		}

		for (int i = 0; i < PITS_PER_PLAYER; i++) {
			if (i < PITS_PER_PLAYER - 1) {
				gamePlayer2Pit[i].setNextPit(gamePlayer2Pit[i + 1]);
			} else {
				gamePlayer2Pit[i].setNextPit(gamePlayer2LargePit);
			}
		}
	}

	/**
	 * Set small and large pits for the player1 and player2
	 * 
	 * @param gamePlayer1Pit
	 *            player1 small pit.
	 * @param gamePlayer2Pit
	 *            player2 small pit.
	 * @param gamePlayer1LargePit
	 *            player1 large pit.
	 * @param gamePlayer2LargePit
	 *            player2 large pit.
	 */
	private void setSmallLargePits(SmallPit[] gamePlayer1Pit,
			SmallPit[] gamePlayer2Pit, LargePit gamePlayer1LargePit,
			LargePit gamePlayer2LargePit) {

		player1.setSmallPits(gamePlayer1Pit);
		player2.setSmallPits(gamePlayer2Pit);

		player1.setLargePit(gamePlayer1LargePit);
		player2.setLargePit(gamePlayer2LargePit);
	}

	public GamePlayer getGamePlayer1() {
		return player1;
	}

	public GamePlayer getGamePlayer2() {
		return player2;
	}

	public void resetGame() {
		player1.reset();
		player2.reset();
		player1.setPlayerTurn(true);
		setUndoEnable(true);
	}

	/**
	 * @param player1
	 *            the player1 to set
	 */
	public void setPlayer1(GamePlayer player1) {
		this.player1 = player1;
	}

	/**
	 * @param player2
	 *            the player2 to set
	 */
	public void setPlayer2(GamePlayer player2) {
		this.player2 = player2;
	}

	/**
	 * get the previous data and undo it
	 */
	public void undoOperation() {
		mancalaView.disableUndoButton();

		player1.getLargePit().setNumberOfStones(previousMove.getBigPit1());
		player2.getLargePit().setNumberOfStones(previousMove.getBigPit2());
		for (int i = 0; i < player1.getSmallPits().length; i++) {
			player1.getSmallPits()[i].setNumberOfStones(previousMove
					.getSmallPits1()[i]);
			player2.getSmallPits()[i].setNumberOfStones(previousMove
					.getSmallPits2()[i]);
		}
		if (previousMove.getPlayer().getPlayerName()
				.equals(player1.getPlayerName())) {
			player1.setPlayerTurn(true);
			player2.setPlayerTurn(false);
		} else {
			player1.setPlayerTurn(false);
			player2.setPlayerTurn(true);
		}
		mancalaView.refreshPots1(previousMove.getBigPit1(),
				previousMove.getSmallPits1(), previousMove.getBigPit2(),
				previousMove.getSmallPits2());
		refreshView();
	}

	/**
	 * Redraw the pits, and mancala pits with their number of stones
	 */
	public void refreshView() {
		player1.getLargePit().refreshView();
		player2.getLargePit().refreshView();
		for (AbstractPit pit : player1.getSmallPits()) {
			pit.refreshView();
		}

		for (AbstractPit pit : player2.getSmallPits()) {
			pit.refreshView();
		}

	}

	/**
	 * @return the undoEnable
	 */
	public boolean isUndoEnable() {
		return undoEnable;
	}

	/**
	 * @param undoEnable
	 *            the undoEnable to set
	 */
	public void setUndoEnable(boolean undoEnable) {
		undoListener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
			}
		};
		this.undoEnable = undoEnable;
	}
	/**
	 * Returns the change listener for the undo button
	 * @return undoListener
	 * 				change listener for undo button
	 * 			
	 */
	public ChangeListener getUndoListener() {
		return undoListener;
	}

	public PreviousMove getPreviousMove() {
		return previousMove;
	}

	public MancalaView getMancalaView() {
		return mancalaView;
	}

	/**
	 * Determine winner according to the number of stones present in their mancala pit and other pits
	 * @return true : end game
	 * 		   false : continue game
	 */
	public boolean validateWinner() {
		if (player1.hasNumberOfStones() && player2.hasNumberOfStones())
			return false;
		else {
			if (player1.hasNumberOfStones()) {
				for (SmallPit s : player1.getSmallPits()) {
					player1.getLargePit().addStones(s.removeStones());
				}
			} else if (player2.hasNumberOfStones()) {
				for (SmallPit s : player2.getSmallPits()) {
					player2.getLargePit().addStones(s.removeStones());
				}
			}
		}
		
		String message = "";
		if (player1.getTotalStones() > player2.getTotalStones())
		{
			message = player1.getPlayerName()+" is the Winner with total score of "
					+ player1.getTotalStones()+ " .";
		} 
		else if (player1.getTotalStones() < player2.getTotalStones()) {
			message = player2.getPlayerName()+ " is the Winner with total score of "
					+ player2.getTotalStones()+ " .";
		}
		else
		{
			message = "There is a tie between " + player1.getPlayerName()
					+ " and " + player2.getPlayerName()
					+ " both got the same score";
		}

		JOptionPane.showMessageDialog(null, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
		return true;
	}
}
