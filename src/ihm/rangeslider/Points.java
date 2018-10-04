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

	public Points(List<Home> homeList) throws HeadlessException {
		this.homeList = homeList;
	}

	public void paintComponent(Graphics g) { 
		
		for (int i = 0; i < homeList.size(); i++) {
			g.fillOval(homeList.get(i).getCoordX(), homeList.get(i).getCoordY() , 10, 10);
		}
		
	}

}
