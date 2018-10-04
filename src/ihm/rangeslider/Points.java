package ihm.rangeslider;

import java.awt.Graphics;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Points extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3746104870971787036L;
	private List<Home> homeList = new ArrayList<Home>();
	private int size;
	private int dist;

	public Points(List<Home> homeList, int size, int distMax) throws HeadlessException {
		this.homeList = homeList;
		this.size = size;
		this.dist = distMax;
	}

	public void paintComponent(Graphics g) {

		g.drawLine(size/2 - 10, size/2 - 10, size/2 + 10, size/2 + 10);
		g.drawLine(size/2 - 10, size/2 + 10, size/2 + 10, size/2 - 10);

		g.drawOval(250-dist, 250-dist, 2 * dist, 2 * dist);

		for (int i = 0; i < homeList.size(); i++) {
			g.fillOval(homeList.get(i).getCoordX() - 5, homeList.get(i).getCoordY() - 5, 10, 10);
		}

	}

}
