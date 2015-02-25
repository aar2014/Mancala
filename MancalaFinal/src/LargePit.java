/**
 * This model holds all the stones for respective game player.
 * 
 * Group Project: Mancala. CS-151.
 * 
 * @author Team Alpha: Nirav Patel, Shubhangi Rankhode, Vaishali Shah
 * 
 */
public class LargePit extends AbstractPit {

	/**
	 * Default constructor.
	 * 
	 * @param gamePlayer
	 *            the game player
	 */
	public LargePit(GamePlayer gamePlayer) {
		super();
		this.gamePlayer = gamePlayer;
	}

	@Override
	public AbstractPit getNextPit() {
		return nextPit;
	}

	@Override
	public void moveStones() {
		//do nothing, unexpected operation
	}

	@Override
	public void reset() {
		this.setNumberOfStones(0);
		this.mancalaBoard.initializedStones();
	}

}
