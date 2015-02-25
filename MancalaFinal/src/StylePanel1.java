

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * This class defines a particular style of the mancala board.
 * Group Project: Mancala. CS-151. 
 * @author Team Alpha: Nirav Patel, Shubhangi Rankhode, Vaishali Shah
 */
public class StylePanel1 implements StylePanel {
	
	private JPanel panel;

	public StylePanel1() {
		super();

		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				 Graphics2D g2 = (Graphics2D) g;
				 g2.setColor(Color.ORANGE);
				 g2.fillRect(0, 0, getWidth(), getHeight());
			}
		};

		panel.setLayout(new BorderLayout());

	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public Style getStyle() {
		return Style.STYLE1;
	}

}
