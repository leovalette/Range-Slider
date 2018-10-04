/*
* We were helped by the RangeSlider code of ernieyu (https://github.com/ernieyu/Swing-range-slider)
* After several attempts and wrong way taken, we understood how all worked and we tried to do it ourself
*/

package ihm.rangeslider;

import javax.swing.*;

public class RangeSlider extends JSlider implements _RangeSlider {

    public RangeSlider(int min, int max, int lower, int upper) {
        this.setMinimum(min);
        this.setMaximum(max);
        this.setValue(lower);
        this.setUpperValue(upper);
        setOrientation(HORIZONTAL);
    }

    @Override
    public void updateUI() {
        setUI(new RangeSliderUI(this));
        updateLabelUIs();
    }

    @Override
    public int getValue() {
        return super.getValue();
    }

    @Override
    public int getUpperValue() {
        return getValue() + getExtent();
    }

    @Override
    public void setValue(int value) {
        int oldValue = getValue();
        if (oldValue == value) {
            return;
        }

        int oldExtent = getExtent();
        int newValue = Math.min(Math.max(getMinimum(), value), oldValue + oldExtent);
        int newExtent = oldExtent + oldValue - newValue;

        getModel().setRangeProperties(newValue, newExtent, getMinimum(),
                getMaximum(), getValueIsAdjusting());
    }

    @Override
    public void setUpperValue(int upper) {
        int lowerValue = getValue();
        int newExtent = Math.min(Math.max(0, upper - lowerValue), getMaximum() - lowerValue);

        setExtent(newExtent);
    }
}
