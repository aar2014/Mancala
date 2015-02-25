/**
 * This the Abstract Pit which extends all the pits of Mancala Board.
 * 
 * Group Project: Mancala. CS-151.
 * 
 * @author Team Alpha: Nirav Patel, Shubhangi Rankhode, Vaishali Shah
 */
public abstract class AbstractPit {

	protected int numberOfStones;
	
	
	protected GamePlayer gamePlayer;

	
	protected MancalaBoard mancalaBoard;

	
	protected AbstractPit nextPit;
	
	/**
	 * Abstract method to be implemented by extending class to move stones to
	 * next pit.
	 */
	abstract public void moveStones();

	/**
	 * Abstract method to be implemented by extending class to reset to default
	 * state.
	 */
	abstract public void reset();

	/**
	 * Abstract method to be implemented by extending class to actually get next
	 * pit.
	 * 
	 * @return the next pit
	 */
	abstract public AbstractPit getNextPit();

	public int getNumberOfStones() {
		return numberOfStones;
	}

	public void setNumberOfStones(int numberOfStones) {
		this.numberOfStones = numberOfStones;
	}

	public GamePlayer getGamePlayer() {
		return gamePlayer;
	}

	public void setMancalaBoard(MancalaBoard mancalaBoard) {
		this.mancalaBoard = mancalaBoard;
	}

	public void setNextPit(AbstractPit nextPit) {
		this.nextPit = nextPit;
	}

	/**
	 * Add number of stones to the pit.
	 * 
	 * @param numberOfStones
	 *            the number of stones
	 */
	public void addStones(final int numberOfStones) {
		this.numberOfStones += numberOfStones;
		mancalaBoard.addStones(numberOfStones);
	}

	/**
	 * Refresh the view of the Mancala Board.
	 */
	public void refreshView() {
		this.mancalaBoard.update();
	}

}
