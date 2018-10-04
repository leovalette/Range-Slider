package ihm.rangeslider;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

	final static int minRooms = 0;
	final static int maxRooms = 10;
	final static int minPrice = 1000;
	final static int maxPrice = 1000000;
	final static int minCoord = 0;
	final static int maxCoord = 500;
	final static int nbHomes = 20;
	private static int size = 500;

	private static List<Home> homeList = new ArrayList<Home>();

	public static void main(String[] args) {
		for (int i = 0; i < nbHomes; i++) {
			int randomRooms = minRooms + (int) (Math.random() * ((maxRooms - minRooms) + 1));
			int randomPrice = minPrice + (int) (Math.random() * ((maxPrice - minPrice) + 1));
			int randomXCoord = minCoord + (int) (Math.random() * ((maxCoord - minCoord) + 1));
			int randomYCoord = minCoord + (int) (Math.random() * ((maxCoord - minCoord) + 1));
			homeList.add(new Home(randomXCoord, randomYCoord, randomRooms, randomPrice));
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main().setVisible(true);
			}
		});
		/*
		 * Window win = new Window(homeList); RangeSliderDemo rangeSlider1 = new
		 * RangeSliderDemo(0, 10, 2, 8, "RangeSlider1"); JSplitPane splitPane = new
		 * JSplitPane(JSplitPane.HORIZONTAL_SPLIT, win, rangeSlider1);
		 */

	}

	public Main() {

		//this.checkCorrectHomes(homeList);
		//this.setContentPane(new Points(otherHomeList, correctHomeList, size, dist));

		JFrame main = new JFrame();

		main.setResizable(false);
		main.setLocationRelativeTo(null);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel slider1 = new JPanel();
		slider1.setBackground(Color.gray);
		slider1.setBounds(size+10,20,400,100);
		slider1.add(new RangeSliderDemo(0, 10, 2, 8, "Rangeslider1"));
		
		JPanel slider2 = new JPanel();
		slider2.setBackground(Color.yellow);
		slider2.setBounds(size+10,120,400,100);
		slider2.add(new RangeSliderDemo(0, 10, 2, 8, "Rangeslider2"));
		
		JPanel slider3 = new JPanel();
		slider3.setBackground(Color.red);
		slider3.setBounds(size+10,220,400,100);
		slider3.add(new RangeSliderDemo(0, 10, 2, 8, "Rangeslider3"));

		JPanel window = new JPanel();
		window.setBackground(Color.blue); 
		window.setBounds(20,20,size+20,size+20);   
		//window.add(new Window(homeList));

		main.add(window);
		main.add(slider1);
		main.add(slider2);
		main.add(slider3);
		main.setSize(1000, 600);
		main.setLayout(null);
		main.setVisible(true);
	}

	/*public void checkCorrectHomes(List<Home> homeList) {
		correctHomeList.clear();
		otherHomeList.clear();
		for (int i = 0; i < homeList.size(); i++) {
			if (Math.abs(homeList.get(i).getCoordX() - size / 2)
					+ Math.abs(homeList.get(i).getCoordY() - size / 2) <= dist && homeList.get(i).getPrice() >= minPrice
					&& homeList.get(i).getPrice() <= maxPrice && homeList.get(i).getNbRooms() >= minRooms
					&& homeList.get(i).getNbRooms() <= maxRooms) {
				correctHomeList.add(homeList.get(i));
			} else {
				otherHomeList.add(homeList.get(i));
			}
		}
	}*/

}
