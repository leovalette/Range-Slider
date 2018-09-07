package ihm.rangeslider;

public interface _RangeSlider {

    int getMinimumCursor();
    int getMaximumCursor();
    void setMinimumCursor(int min);
    void setMaximumCursor(int max);

    int getMinimum();
    int getMaximum();
    void setMinimum(int min);
    void setMaximum(int max);

    boolean checkValues();

    int barSize();
}
