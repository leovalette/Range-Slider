package ihm.rangeslider;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;

public class RangeSliderUI extends BasicSliderUI {
    private RangeSlider slider;
    private JLabel label;

    public RangeSliderUI()
    {
        super("RangeSlider");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(new FlowLayout());
        slider = new RangeSlider(1, 10);
        label = new JLabel("MAX : "+ slider.getMaximum());
        add(label);
        add(slider);
        slider.setValue(slider.getMaximum());
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                slider.setMaximum(slider.getValue());
                label.setText("MAX : " + String.valueOf(slider.getMaximum()));
            }
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}