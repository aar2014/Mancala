import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * This is the Mancala Large Pit which initialize stones and define the Styles
 * for the mancala.
 * 
 * Group Project: Mancala. CS-151.
 * 
 * @author Team Alpha: Nirav Patel, Shubhangi Rankhode, Vaishali Shah
 */

public class MancalaLargePit extends MancalaBoard {
	/**
	 * Default Constructor.
	 * 
	 * @param numOfStone
	 *            total number of stones.
	 * @param style
	 *            style of the game.
	 */
	public MancalaLargePit(int numOfStone, Style style) {
		super(true, numOfStone, style);
		this.setPreferredSize(new Dimension(170, 300));
	}

	@Override
	public void initializedStones() {
		stones = new ArrayList<Stone>();
		update();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		FontMetrics fontMetrics = g.getFontMetrics();
		Rectangle2D rectangle = fontMetrics.getStringBounds(abstractPit
				.getGamePlayer().getPlayerName(), g);
		int textWidth = (int) rectangle.getWidth();
		g.drawString(abstractPit.getGamePlayer().getPlayerName(),
				(getWidth() - textWidth) / 2, 20);
	}
}
