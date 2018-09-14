package ihm.rangeslider;

import javax.swing.*;

public class RangeSlider extends JSlider implements _RangeSlider{
    private int MaximumCursor;
    private int MinimumCursor;
    private int Maximum;
    private int Minimum;

    RangeSlider(int minCursor, int maxCursor, int min, int max) {
        this.MaximumCursor = maxCursor;
        this.MinimumCursor = minCursor;
        this.Maximum = max;
        this.Minimum = min;
    }

    @Override
    public int getMinimumCursor() {
        return this.MinimumCursor;
    }

    @Override
    public int getMaximumCursor() {
        return this.MaximumCursor;
    }

    @Override
    public void setMinimumCursor(int min) {
        this.MinimumCursor = min;
    }

    @Override
    public void setMaximumCursor(int max) {
        this.MaximumCursor = max;
    }

    @Override
    public int getMinimum() {
        return this.Minimum;
    }

    @Override
    public int getMaximum() {
        return this.Maximum;
    }

    @Override
    public void setMinimum(int min) {
        this.Minimum = min;
    }

    @Override
    public void setMaximum(int max) {
        this.Maximum = max;
    }

    @Override
    public boolean checkValues() {
        return getMinimumCursor() < getMaximumCursor();
    }

    @Override
    public int barSize() {
        return getMaximumCursor() - getMinimumCursor();
    }
}
