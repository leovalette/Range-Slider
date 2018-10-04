/*
 * We were helped by the RangeSlider code of ernieyu (https://github.com/ernieyu/Swing-range-slider)
 * After several attempts and wrong way taken, we understood how all worked and we tried to do it ourself
 */

package ihm.rangeslider;

import javax.swing.*;
import java.awt.*;

public class RangeSliderDemo extends JPanel{

    private JLabel rangeSliderLowerValue = new JLabel();
    private JLabel rangeSliderUpperValue = new JLabel();

    private RangeSlider rangeSlider;

    private RangeSliderDemo(int min, int max, int lower, int upper) {
        setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        setLayout(new GridBagLayout());

        JLabel rangeSliderLowerLabel = new JLabel();
        rangeSliderLowerLabel.setText("Lower Value:");

        JLabel rangeSliderUpperLabel = new JLabel();
        rangeSliderUpperLabel.setText("Upper Value:");

        rangeSlider = new RangeSlider(min, max, lower, upper);

        this.rangeSliderLowerValue.setHorizontalAlignment(JLabel.LEFT);
        this.rangeSliderUpperValue.setHorizontalAlignment(JLabel.LEFT);

        add(rangeSliderLowerLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 3, 3), 0, 0));

        add(this.rangeSliderLowerValue, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 3, 3), 0, 0));

        add(rangeSliderUpperLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 3, 3), 0, 0));

        add(this.rangeSliderUpperValue, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 3, 3), 0, 0));

        add(this.rangeSlider, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    }

    private void display() {
        this.rangeSliderLowerValue.setText(String.valueOf(this.rangeSlider.getValue()));
        this.rangeSliderUpperValue.setText(String.valueOf(this.rangeSlider.getUpperValue()));

        rangeSlider.addChangeListener(e -> {
            RangeSlider rangeSlider = (RangeSlider) e.getSource();
            this.rangeSliderLowerValue.setText(String.valueOf(rangeSlider.getValue()));
            this.rangeSliderUpperValue.setText(String.valueOf(rangeSlider.getUpperValue()));
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
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new RangeSliderDemo(0, 10, 2, 8).display());
    }
}