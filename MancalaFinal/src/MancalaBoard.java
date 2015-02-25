import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;

/**
 * This is the MancalaBoard which add the actions to the pits and buttons of the
 * Mancala Game.
 * 
 * Group Project: Mancala. CS-151.
 * 
 * @author Team Alpha: Nirav Patel, Shubhangi Rankhode, Vaishali Shah
 */
public class MancalaBoard extends JComponent {
	private int stoneCount;

	protected List<Stone> stones;

	protected AbstractPit abstractPit;

	private Style style;

	final boolean isBottom;

	public boolean m;

	protected boolean stonesInitialized;

	/**
	 * Default Constructor for the Mancala Board.
	 * 
	 * @param isBottom
	 *            position of the stones.
	 * @param stoneCount
	 *            total count of stones in pits.
	 * @param style
	 *            different style for mancala.
	 */
	public MancalaBoard(boolean isBottom, int stoneCount, Style style) {
		this.isBottom = isBottom;
		m = false;
		stonesInitialized = false;
		this.stoneCount = stoneCount;
		registerMouseListener();
		this.style = style;
	}

	/**
	 * This method initializes each regular pit with selected number of stones
	 */
	public void initializedStones() {
		stones = new ArrayList<Stone>();
		addStones(stoneCount);
	}

	/**
	 * This method is used to add stones
	 * 
	 * @param amount
	 *            is the number of stones
	 */
	public void addStones(int numOfStones) {
		Random r = new Random();

		//long t = System.currentTimeMillis();

		for (int i = 0; i < numOfStones; i++) {
			int x = (int) ((getWidth() - 15) * 0.25)
					+ r.nextInt((int) ((getWidth() - 15) * 0.5));
			int y = (int) ((getHeight() - 15) * 0.25)
					+ r.nextInt((int) ((getHeight() - 15) * 0.5));

			Stone stone = new Stone(1.0 * x / getWidth(), 1.0 * y / getHeight());
			stones.add(stone);
		}
		update();
	}

	/**
	 * This method is used to remove stones.
	 */
	public void removeStones() {
		stones.clear();
		update();
	}

	/**
	 * This method is used to set pits
	 * 
	 * @param abstractPit
	 *            refers to class AbstractPit
	 */
	public void setPit(AbstractPit abstractPit) {
		this.abstractPit = abstractPit;
		abstractPit.setMancalaBoard(this);
	}

	/**
	 * get the event when you click on the pit.
	 * 
	 * @return event after click
	 */
	private boolean getClick() {
		boolean click = abstractPit != null && stones != null
				&& abstractPit.getGamePlayer().isPlayerTurn()
				&& !stones.isEmpty()
				&& !abstractPit.getGamePlayer().isMovingStones()
				&& !(this instanceof MancalaLargePit);
		return click;
	}

	/**
	 * This method adds mouse listeners and defines their functionality.
	 */
	protected void registerMouseListener() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (getClick()) {
					m = true;
					update();
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				m = false;
				update();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (getClick()) {
					m = false;
					abstractPit.moveStones();
					update();
				}
			}
		});
	}

	/**
	 * override the paint method with different styles on the mancala.
	 * 
	 * @param g
	 *            paint component.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (Style.STYLE1 == style) {
			StylePit1 t = new StylePit1(this);
			t.pitsStyle(g);
		} else if (Style.STYLE2 == style) {
			StylePit2 t = new StylePit2(this);
			t.pitsStyle(g);
		}

	}

	/**
	 * update the paint method.
	 */
	protected void update() {
		this.repaint();
	}
}
