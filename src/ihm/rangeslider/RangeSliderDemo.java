package ihm.rangeslider;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;

public class RangeSliderDemo {
    public static void main(String[] args) {
        new BasicSliderUI(new JSlider());
        new RangeSliderUI();
    }
}
