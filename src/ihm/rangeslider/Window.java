package ihm.rangeslider;

import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame {
	private RangeSlider slider;
	private JLabel label;
	private List<Home> homeList = new ArrayList<Home>();

	public Window(List<Home> homeList) throws HeadlessException {
		this.homeList = homeList;
		JFrame window = new JFrame();
		window.setTitle("Homes");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new Point(homeList.get(0).getCoordX(),homeList.get(0).getCoordY()));
		this.setVisible(true);
	}
}