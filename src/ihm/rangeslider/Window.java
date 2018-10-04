package ihm.rangeslider;

import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2160936093279208040L;
	private static int size = 500;

	private int minPrice = 50000;
	private int maxPrice = 750000;
	private int dist = 150;
	private int minRooms = 3;
	private int maxRooms = 10;

	private List<Home> correctHomeList = new ArrayList<Home>();
	private List<Home> otherHomeList = new ArrayList<Home>();

	public Window(List<Home> homeList) throws HeadlessException {
		JFrame window = new JFrame();
		window.setTitle("Homes");
		this.setSize(size, size);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.checkCorrectHomes(homeList);
		this.setContentPane(new Points(otherHomeList,correctHomeList, size, dist));
		this.setVisible(true);
	}
	
	public void checkCorrectHomes(List<Home> homeList) {
		correctHomeList.clear();
		otherHomeList.clear();
		for (int i = 0; i < homeList.size(); i++) {
			if( Math.abs(homeList.get(i).getCoordX()- size/2) + Math.abs(homeList.get(i).getCoordY()-size/2) <= dist
					&& homeList.get(i).getPrice() >= minPrice && homeList.get(i).getPrice() <= maxPrice
					&& homeList.get(i).getNbRooms() >= minRooms && homeList.get(i).getNbRooms() <= maxRooms) {
				correctHomeList.add(homeList.get(i));
			}
			else {
				otherHomeList.add(homeList.get(i));
			}
		}
	}
	
}