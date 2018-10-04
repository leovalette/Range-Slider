package ihm.rangeslider;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private int dist = 150;

	private static List<Home> homeList = new ArrayList<Home>();
	private List<Home> correctHomeList = new ArrayList<Home>();
	private List<Home> otherHomeList = new ArrayList<Home>();
	RangeSliderDemo rs1 = new RangeSliderDemo(minPrice, maxPrice, 20000, 800000, "Prix :");
	RangeSliderDemo rs2 = new RangeSliderDemo(minRooms, maxRooms, 2, 8, "Nombre de chambres :");
	RangeSliderDemo rs3 = new RangeSliderDemo(minCoord, maxCoord, 10, 200, "Distance :");

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
				new Main();
			}
		});
		/*
		 * Window win = new Window(homeList); RangeSliderDemo rangeSlider1 = new
		 * RangeSliderDemo(0, 10, 2, 8, "RangeSlider1"); JSplitPane splitPane = new
		 * JSplitPane(JSplitPane.HORIZONTAL_SPLIT, win, rangeSlider1);
		 */

	}

	public Main() {

		this.checkCorrectHomes(homeList);
		JFrame main = new JFrame();
		main.setTitle("Homes");
		main.setResizable(false);
		main.setLocationRelativeTo(null);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setContentPane(new Points(otherHomeList, correctHomeList, size, rs3.getRangeSlider().getUpperValue()));

		JPanel slider1 = new JPanel();
		slider1.setBackground(Color.gray);
		slider1.setBounds(size + 10, 20, 400, 100);
		slider1.add(rs1);

		JPanel slider2 = new JPanel();
		slider2.setBackground(Color.yellow);
		slider2.setBounds(size + 10, 120, 400, 100);
		slider2.add(rs2);

		JPanel slider3 = new JPanel();
		slider3.setBackground(Color.red);
		slider3.setBounds(size + 10, 220, 400, 100);
		slider3.add(rs3);

		main.add(slider1);
		main.add(slider2);
		main.add(slider3);
		main.setSize(1000, 600);
		main.setLayout(null);
		main.setVisible(true);
	}

	public void checkCorrectHomes(List<Home> homeList) {
		correctHomeList.clear();
		otherHomeList.clear();
		for (int i = 0; i < homeList.size(); i++) {
			if (Math.abs(homeList.get(i).getCoordX() - size / 2)
					+ Math.abs(homeList.get(i).getCoordY() - size / 2) >= rs3.getRangeSlider().getValue()
					&& Math.abs(homeList.get(i).getCoordX() - size / 2)
							+ Math.abs(homeList.get(i).getCoordY() - size / 2) <= rs3.getRangeSlider().getUpperValue()
					&& homeList.get(i).getPrice() >= rs1.getRangeSlider().getValue() && homeList.get(i).getPrice() <= rs1.getRangeSlider().getUpperValue()
					&& homeList.get(i).getNbRooms() >= rs2.getRangeSlider().getValue() && homeList.get(i).getNbRooms() <= rs2.getRangeSlider().getUpperValue()) {
				correctHomeList.add(homeList.get(i));
			} else {
				otherHomeList.add(homeList.get(i));
			}
		}
	}

}
