

import java.awt.*;


/**
 * This class defines a particular style and color for the pits and stones. 
 * Group Project: Mancala. CS-151.
 * @author Team Alpha: Nirav Patel, Shubhangi Rankhode, Vaishali Shah
 */
public class StylePit2 {
	 
    MancalaBoard board;
 
    public StylePit2(MancalaBoard board) {
        super();
        this.board = board;
    }
 /**
  * Draw pit
  * @param g
  */
    public void pitsStyle(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (!board.m) 
        {
            g2.setColor(Color.ORANGE);
        } else 
        {
            g2.setColor(Color.BLACK);
        }
 
        g2.fillOval((int) ((board.getWidth() * 0.1)), (int) ((board.getHeight() * 0.1) + 5),(int) (board.getWidth() * 0.8), (int) ((board.getHeight() * 0.8))-10);

        g2.setColor(Color.BLUE);
        if (!board.stonesInitialized) {
            board.stonesInitialized = true;
            board.initializedStones();
        }
      
        for (Stone s : board.stones) 
        {
            int x = (int) (s.getX() * board.getWidth());
            int y = (int) (s.getY() * board.getHeight());

            g2.setColor(Color.blue);
 
            g2.fillOval(x, y, 7, 7);
        }
        // Font Color
        g2.setColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 15);
        g2.setFont(font);
        if (board.isBottom) 
        {
            g2.drawString(board.stones.size() + "", (int) board.getWidth() / 2, board.getHeight() - 5);

        } else 
        {
            g2.drawString(board.stones.size() + "", (int) board.getWidth() / 2, 15);
        }
     
    }
}