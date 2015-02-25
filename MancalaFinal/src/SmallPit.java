/**
 * This model holds all the stones for respective game player in small pit.
 * 
 * Group Project: Mancala. CS-151.
 * 
 * @author Team Alpha: Nirav Patel, Shubhangi Rankhode, Vaishali Shah
 */
public class SmallPit extends AbstractPit {

	protected SmallPit otherPlayersSmallPit;

	/**
	 * Default constructor.
	 * 
	 * @param gamePlayer
	 *            the game player
	 */
	public SmallPit(GamePlayer gamePlayer) {
		super();
		this.gamePlayer = gamePlayer;
	}

	public SmallPit getOtherPlayersSmallPit() {
		return this.otherPlayersSmallPit;
	}

	public void setOtherPlayersSmallPit(SmallPit smallPit) {
		this.otherPlayersSmallPit = smallPit;
	}

	@Override
	public AbstractPit getNextPit() {
		return nextPit;
	}

	/**
	 * This is the inner class that helps move the stones
	 * at every step. Validates the game rules and collects the winnings.
	 * 
	 */
	public class Move {

		public void startMoving() {
			// set previous move for undo
			previousMove();

			SmallPit.this.gamePlayer.startMovingStones();

			AbstractPit current = moveToNextPit();
			if (current instanceof SmallPit) {
				verifyAndCollectWinnings(current);

				SmallPit.this.gamePlayer.setPlayerTurn(false);
				if (!SmallPit.this.gamePlayer.getMancalaController()
						.validateWinner()) {
					if (SmallPit.this.gamePlayer == SmallPit.this.gamePlayer
							.getMancalaController().getGamePlayer1()) {
						SmallPit.this.gamePlayer.getMancalaController()
								.getGamePlayer2().setPlayerTurn(true);
					} else {
						SmallPit.this.gamePlayer.getMancalaController()
								.getGamePlayer1().setPlayerTurn(true);
					}
				}
				SmallPit.this.gamePlayer.endMovingStones();

				return;

			}

			if (SmallPit.this.gamePlayer.getMancalaController()
					.validateWinner()) {
				SmallPit.this.gamePlayer.setPlayerTurn(false);
			}
			SmallPit.this.gamePlayer.endMovingStones();

		}

		/**
		 * Verify and collect all the winning stones.
		 * 
		 * RULE: If current number of stones is 1 and current player is same as
		 * small pits player. Then collect the stones and place it into current
		 * game players pit.
		 * 
		 * @param current
		 */
		private void verifyAndCollectWinnings(AbstractPit current) {

			if (current.getNumberOfStones() == 1
					&& current.gamePlayer == SmallPit.this.gamePlayer) {

				int removeStonesCurrent = ((SmallPit) current).removeStones();
				int removeStonesOtherPlayerSmallPit = ((SmallPit) current)
						.getOtherPlayersSmallPit().removeStones();

				SmallPit.this.gamePlayer.getLargePit().addStones(
						removeStonesCurrent + removeStonesOtherPlayerSmallPit);
			}
		}

		/**
		 * Move to next pit and add 1 stone until all the stones are segregated.
		 * 
		 * @return current abstract pit
		 */
		private AbstractPit moveToNextPit() {
			AbstractPit current = SmallPit.this;
			int numberOfStonesRemoved = removeStones();
			while (numberOfStonesRemoved > 0) {
				current = current.getNextPit();
				current.addStones(1);
				numberOfStonesRemoved--;
			}
			return current;
		}

		/**
		 * Defines the previous move for the player to do undo operation.s
		 */
		private void previousMove() {
			GamePlayer player1 = SmallPit.this.gamePlayer
					.getMancalaController().getGamePlayer1();
			GamePlayer player2 = SmallPit.this.gamePlayer
					.getMancalaController().getGamePlayer2();
			PreviousMove previousMove = SmallPit.this.gamePlayer
					.getMancalaController().getPreviousMove();
			MancalaView mancalaView = SmallPit.this.gamePlayer
					.getMancalaController().getMancalaView();

			GamePlayer currentMovePlayer = SmallPit.this.gamePlayer;
			if (previousMove.getPlayer() == currentMovePlayer) {
				previousMove.incrementUndoCount();
				if (previousMove.getUndoCount() >= 3) {
					mancalaView.disableUndoButton();
				}
			} else {
				previousMove.setUndoCount(0);
				mancalaView.enableUndoButton();
			}

			previousMove.setPlayer(currentMovePlayer);
			previousMove.setBigPit1(new Integer(player1.getLargePit()
					.getNumberOfStones()));
			previousMove.setBigPit2(new Integer(player2.getLargePit()
					.getNumberOfStones()));
			Integer[] smallPit1 = new Integer[player1.getSmallPits().length];
			Integer[] smallPit2 = new Integer[player2.getSmallPits().length];
			for (int i = 0; i < player1.getSmallPits().length; i++) {
				smallPit1[i] = new Integer(
						player1.getSmallPits()[i].getNumberOfStones());
				smallPit2[i] = new Integer(
						player2.getSmallPits()[i].getNumberOfStones());
			}
			previousMove.setSmallPits1(smallPit1);
			previousMove.setSmallPits2(smallPit2);
		}

	}

	@Override
	public void moveStones() {
		new Move().startMoving();
	}

	/**
	 * Removes stones from the board.
	 * 
	 * @return the number of stones removed
	 */
	public int removeStones() {
		int tmp = this.numberOfStones;
		this.numberOfStones = 0;
		mancalaBoard.removeStones();
		return tmp;
	}

	@Override
	public void reset() {
		this.setNumberOfStones(4);
		this.mancalaBoard.initializedStones();
	}

}
