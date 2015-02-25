import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * This class initializes the view/frame of Mancala Board Group Project:
 * Mancala. CS-151.
 * 
 * @author Team Alpha: Nirav Patel, Shubhangi Rankhode, Vaishali Shah
 */

public class MancalaView {
	private MancalaBoard[] mancalaSmallPits;
	private MancalaBoard mancalaPit1;
	private MancalaBoard mancalaPit2;
	private MancalaController mancalaController;
	private Style style;
	JButton undo;
	JFrame frame;
	int stoneCount;

	public MancalaView(MancalaController mancalaController, int stoneCount) {
		this.mancalaController = mancalaController;
		this.stoneCount = stoneCount;
		frame = new JFrame("Mancala");
		frame.setSize(900, 280);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	/**
	 * This method creates JPanel based on Style panel selected by the user.
	 * 
	 * @param selected
	 *            refers to style panel
	 */
	public void selectStyle(StylePanel selected) {
		Container content = frame.getContentPane();
		content.setLayout(new BorderLayout());
		generatePits(selected.getStyle());

		JPanel center = new JPanel(new GridLayout(2, 6));
		center.setOpaque(false);

		JPanel panel = selected.getPanel();
		panel.add(center, BorderLayout.CENTER);
		addPots(center, panel);
		content.add(panel, BorderLayout.CENTER);
		addButtonPanel();
		frame.setVisible(true);
		frame.setVisible(false);
		frame.setVisible(true);
	}

	/**
	 * This method updates the number of stones in mancala pit
	 */
	public void refreshBigPots() {
		mancalaPit1.update();
		mancalaPit2.update();
	}

	/**
	 * This method creates a menu bar in the frame. Buttons and their
	 * corresponding action listeners are added in this menu bar.
	 * 
	 * @param frame
	 *            is the JFrame in which menu bar is added.
	 */
	private void addButtonPanel() {
		JPanel buttonHolder = new JPanel();
		JButton newGame = new JButton("NEW GAME");
		undo = new JButton("UNDO");
		JButton exit = new JButton("Exit");
		buttonHolder.add(newGame);
		buttonHolder.add(undo);
		buttonHolder.add(exit);
		buttonHolder.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.add(buttonHolder, BorderLayout.NORTH);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!mancalaController.getGamePlayer1().isMovingStones()
						&& !mancalaController.getGamePlayer2().isMovingStones()) {
					mancalaController.resetGame();
					undo.setEnabled(true);
				}
			}
		});

		undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				mancalaController.undoOperation();
				undo.setEnabled(mancalaController.isUndoEnable());
			}
		});
		undo.addChangeListener(mancalaController.getUndoListener());

	}

	/**
	 * This method enables the function of "Undo" button
	 */
	public void enableUndoButton() {
		undo.setEnabled(true);
	}

	/**
	 * This method disables the function of "Undo" button
	 */
	public void disableUndoButton() {
		undo.setEnabled(false);
	}

	/**
	 * This method regular pits and mancala pits in the JPanel
	 * 
	 * @param center
	 *            is the JPanel for regular pits
	 * @param panel
	 *            is the JPanel for Mancala Pits
	 */
	private void addPots(JPanel center, JPanel panel) {
		panel.add(mancalaPit1, BorderLayout.EAST);
		panel.add(mancalaPit2, BorderLayout.WEST);
		for (int i = 11; i > 5; i--) {
			center.add(mancalaSmallPits[i]);
		}
		for (int i = 0; i < 6; i++) {
			center.add(mancalaSmallPits[i]);
		}
	}

	/**
	 * This method creates mancala pits and regular pits.
	 * 
	 * @param style
	 *            refers to Mancala Board style selected by the user.
	 */
	private void generatePits(Style style) {
		this.style = style;
		mancalaPit1 = new MancalaLargePit(stoneCount, style);
		mancalaPit1.setPit(mancalaController.getGamePlayer1().getLargePit());
		mancalaPit2 = new MancalaLargePit(stoneCount, style);
		mancalaPit2.setPit(mancalaController.getGamePlayer2().getLargePit());

		mancalaSmallPits = new MancalaBoard[12];
		for (int i = 0; i < mancalaSmallPits.length; i++) {
			if (i < 6) {
				mancalaSmallPits[i] = new MancalaBoard(true, stoneCount, style);
				mancalaSmallPits[i].setPit(mancalaController.getGamePlayer1()
						.getSmallPits()[i]);
			} else {
				mancalaSmallPits[i] = new MancalaBoard(false, stoneCount, style);
				mancalaSmallPits[i].setPit(mancalaController.getGamePlayer2()
						.getSmallPits()[i - 6]);
			}
		}
	}

	/**
	 * This method refreshes the regular pits and mancala pits as the game
	 * progresses.
	 * 
	 * @param bigPotCount1
	 *            is the number of stones in Player 1's mancala pit
	 * @param smallPotCount1
	 *            is the array of Integers which keeps track on number of stones
	 *            in regular pits of Player 1
	 * @param bigPotCount2
	 *            is the number of stones in Player 2's mancala pit
	 * @param smallPotCount2
	 *            is the array of Integers which keeps track on number of stones
	 *            in regular pits of Player 2
	 */
	public void refreshPots(Integer bigPotCount1, Integer[] smallPotCount1,
			Integer bigPotCount2, Integer[] smallPotCount2) {
		mancalaPit1 = new MancalaLargePit(bigPotCount1, style);
		mancalaPit1.setPit(mancalaController.getGamePlayer1().getLargePit());
		mancalaPit2 = new MancalaLargePit(bigPotCount2, style);
		mancalaPit2.setPit(mancalaController.getGamePlayer2().getLargePit());

		mancalaSmallPits = new MancalaBoard[12];
		for (int i = 0; i < mancalaSmallPits.length; i++) {
			if (i < 6) {
				mancalaSmallPits[i] = new MancalaBoard(true, smallPotCount1[i],
						style);
				mancalaSmallPits[i].setPit(mancalaController.getGamePlayer1()
						.getSmallPits()[i]);
			} else {
				mancalaSmallPits[i] = new MancalaBoard(false,
						smallPotCount2[i - 6], style);
				mancalaSmallPits[i].setPit(mancalaController.getGamePlayer2()
						.getSmallPits()[i - 6]);
			}
		}
	}

	/**
	 * This method updates the count of stones in the regular pits and mancala
	 * pits.
	 * 
	 * @param bigPotCount1
	 *            is the number of stones in Player 1's mancala pit
	 * @param smallPotCount1
	 *            is the array of Integers which keeps track on number of stones
	 *            in regular pits of Player 1
	 * @param bigPotCount2
	 *            is the number of stones in Player 2's mancala pit
	 * @param smallPotCount2
	 *            is the array of Integers which keeps track on number of stones
	 *            in regular pits of Player 2
	 */
	public void refreshPots1(Integer bigPotCount1, Integer[] smallPotCount1,
			Integer bigPotCount2, Integer[] smallPotCount2) {
		updatePotCount(mancalaPit1, bigPotCount1);
		updatePotCount(mancalaPit2, bigPotCount2);

		for (int i = 0; i < mancalaSmallPits.length; i++) {
			if (i < 6) {
				updatePotCount(mancalaSmallPits[i], smallPotCount1[i]);
			} else {
				updatePotCount(mancalaSmallPits[i], smallPotCount2[i - 6]);
			}
		}
	}

	/**
	 * This method adds and removes stones from the pits.
	 * 
	 * @param mancalaBoard
	 *            is the mancala board.
	 * @param newCount
	 *            is the number of stones
	 */
	public void updatePotCount(MancalaBoard mancalaBoard, Integer newCount) {
		int currCount = mancalaBoard.stones.size();
		if (currCount > newCount) {
			mancalaBoard.removeStones();
			mancalaBoard.addStones(newCount);
		}
		if (currCount < newCount) {
			mancalaBoard.addStones(newCount - currCount);
		}
	}

	public void refreshView() {

	}

}
