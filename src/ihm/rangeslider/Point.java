package ihm.rangeslider;

import java.awt.Graphics;
import javax.swing.JPanel;

public class Point extends JPanel {

	private int coordX;
	private int coordY;

	public Point(int coordX, int coordY) {
		super();
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public void paintComponent(Graphics g) { 
		g.fillOval(coordX, coordY, 10, 10);
	}

}
