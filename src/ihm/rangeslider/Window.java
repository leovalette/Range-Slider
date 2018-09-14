package ihm.rangeslider;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Window extends JFrame {
    private RangeSlider slider;
    private JLabel label;

    public Window()
    {
        super("RangeSlider");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(new FlowLayout());
        slider = new RangeSlider(2, 4, 1, 7);
        label = new JLabel("MAX : "+ slider.getMaximumCursor());
        add(label);
        add(slider);
        slider.setValue(slider.getMaximumCursor());
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                slider.setMaximumCursor(slider.getValue());
                label.setText("MAX : " + String.valueOf(slider.getMaximumCursor()));
            }
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args)
    {
        new Window();
    }
}
