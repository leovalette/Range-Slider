package ihm.rangeslider;

import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JFrame;

public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2160936093279208040L;

	public Window(List<Home> homeList) throws HeadlessException {
		JFrame window = new JFrame();
		window.setTitle("Homes");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new Points(homeList));
		this.setVisible(true);
	}
}