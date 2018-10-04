/*
 * We was helped by the RangeSlider code of ernieyu (https://github.com/ernieyu/Swing-range-slider)
 * After several attempts and wrong way taken, we understood how all worked and we tried to do it ourself
 */

package ihm.rangeslider;

import javax.swing.*;
import java.awt.*;

public class RangeSliderDemo extends JPanel{

    private JLabel sliderValue = new JLabel();

    private JSlider slider = new JSlider(0, 10);

    private RangeSliderDemo() {
        setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        setLayout(new GridBagLayout());

        JLabel sliderLabel = new JLabel();
        sliderLabel.setText("Slider value:");

        this.sliderValue.setHorizontalAlignment(JLabel.LEFT);

        add(sliderLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 3, 3), 0, 0));

        add(this.sliderValue, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 3, 3), 0, 0));

        add(this.slider, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    }

    private void display() {
        this.slider.setValue(5);

        this.sliderValue.setText(String.valueOf(this.slider.getValue()));

        slider.addChangeListener(e -> {
            JSlider slider = (JSlider) e.getSource();
            sliderValue.setText(String.valueOf(slider.getValue()));
        });

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("RangeSlider");

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RangeSliderDemo().display());
    }
}
