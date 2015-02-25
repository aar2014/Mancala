/**
 * This model class holds the pit that is placed at the end of each player area.
 * 
 * Group Project: Mancala CS-151
 * 
 * @author Team Alpha: Nirav Patel, Vaishali Shah, Shubhangi Rankhode
 * 
 */
public class EndPit extends SmallPit {

	private AbstractPit otherPlayersPit;

	/**
	 * Default constructor to set game player for the pit placed at the end.
	 * 
	 * @param gamePlayer
	 *            the game player
	 */
	public EndPit(GamePlayer gamePlayer) {
		super(gamePlayer);
	}

	/**
	 * Set the alternate pits for the player according to their turns.
	 * 
	 * @param otherPlayersPit
	 *            the other players pit
	 */
	public void setOtherPlayersPit(AbstractPit otherPlayersPit) {
		this.otherPlayersPit = otherPlayersPit;
	}

	@Override
	public AbstractPit getNextPit() {
		if (gamePlayer.isPlayerTurn()) {
			return nextPit;
		}
		return otherPlayersPit;
	}

}
