/**
 * This model class holds attributes for the game player.
 * 
 * Group Project: Mancala. CS-151.
 * 
 * @author Team Alpha: Nirav Patel, Shubhangi Rankhode, Vaishali Shah
 * 
 */
public class GamePlayer {

	private MancalaController mancalaController;

	private boolean movingStones;

	private LargePit largePit;

	private SmallPit[] smallPits;

	private int undoCount;

	private String playerName;

	private boolean playerTurn;

	/**
	 * Default constructor with mancala controller and player name.
	 * 
	 * @param mancalaController
	 *            the controller
	 * @param playerName
	 *            the player name
	 */
	public GamePlayer(MancalaController mancalaController, String playerName) {
		super();
		this.mancalaController = mancalaController;
		this.playerName = playerName;
		this.playerTurn = false;
		this.movingStones = false;
		this.undoCount = 0;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public boolean isPlayerTurn() {
		return this.playerTurn;
	}

	public void setPlayerTurn(boolean playerTurn) {
		this.playerTurn = playerTurn;
	}

	public boolean isMovingStones() {
		return this.movingStones;
	}

	public void startMovingStones() {
		this.movingStones = true;
	}

	public void endMovingStones() {
		this.movingStones = false;
	}

	/**
	 * Get the total stones in the large pit.
	 * 
	 * @return the total number of stones
	 */
	public int getTotalStones() {
		return this.largePit.getNumberOfStones();
	}

	public SmallPit[] getSmallPits() {
		return smallPits;
	}

	public void setSmallPits(SmallPit[] pits) {
		smallPits = pits;
	}

	public LargePit getLargePit() {
		return largePit;
	}

	public void setLargePit(LargePit pit) {
		largePit = pit;
	}

	/**
	 * Validates if the small pits has any number of stones.
	 * 
	 * @return true if more then one stone found
	 */
	public boolean hasNumberOfStones() {
		for (SmallPit s : this.smallPits) {
			if (s.getNumberOfStones() != 0)
				return true;
		}
		return false;
	}

	/**
	 * Reset pits to its default state with specified number of stones.
	 */
	public void reset() {
		for (SmallPit s : this.smallPits) {
			s.reset();
		}
		largePit.reset();
		this.setPlayerTurn(false);
	}

	public MancalaController getMancalaController() {
		return mancalaController;
	}

	/**
	 * @return the undoCount
	 */
	public int getUndoCount() {
		return undoCount;
	}

	/**
	 * This method sets undoCount to a new value.
	 * 
	 * @param undoCount
	 *            the undoCount to set
	 */
	public void setUndoCount(int undoCount) {
		this.undoCount = undoCount;
	}

}
