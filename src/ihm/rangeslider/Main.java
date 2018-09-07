package ihm.rangeslider;

import javax.swing.*;

public class Main {

    private int MaximumCursor = 4;
    private int MinimumCursor = 2;
    private int Maximum = 7;
    private int Minimum = 1;

    public RangeSlider rangeSlider = new RangeSlider(MaximumCursor, MinimumCursor, Maximum, Minimum);

    public static void main(String[] args) {
        JFrame window = new JFrame("Window");
        window.setContentPane(new Window().getSlider1());
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }
}
