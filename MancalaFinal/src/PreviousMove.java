/**
 * This class defines the previous move for the game player in the Mancala.
 * 
 * Group Project: Mancala. CS-151.
 * 
 * @author Team Alpha: Nirav Patel, Shubhangi Rankhode, Vaishali Shah.
 */
public class PreviousMove {
	private Integer bigPit1;

	private Integer bigPit2;

	private Integer[] smallPits1;

	private Integer[] smallPits2;

	private int undoCount;

	GamePlayer gamePlayer;

	public GamePlayer getPlayer() {
		return gamePlayer;
	}

	public void setPlayer(GamePlayer gamePlayer) {
		this.gamePlayer = gamePlayer;
	}

	public Integer getBigPit1() {
		return bigPit1;
	}

	public void setBigPit1(Integer bigPit1) {
		this.bigPit1 = bigPit1;
	}

	public Integer getBigPit2() {
		return bigPit2;
	}

	public void setBigPit2(Integer bigPit2) {
		this.bigPit2 = bigPit2;
	}

	public Integer[] getSmallPits1() {
		return smallPits1;
	}

	public void setSmallPits1(Integer[] smallPits1) {
		this.smallPits1 = smallPits1;
	}

	public Integer[] getSmallPits2() {
		return smallPits2;
	}

	public void setSmallPits2(Integer[] smallPits2) {
		this.smallPits2 = smallPits2;
	}

	/**
	 * @return the undoCount
	 */
	public int getUndoCount() {
		return undoCount;
	}

	/**
	 * @param undoCount
	 *            the undoCount to set
	 */
	public void setUndoCount(int undoCount) {
		this.undoCount = undoCount;
	}

	/**
	 * increment the undo count.
	 * 
	 * @return the incremented count.
	 */
	public int incrementUndoCount() {
		return undoCount++;
	}
}
