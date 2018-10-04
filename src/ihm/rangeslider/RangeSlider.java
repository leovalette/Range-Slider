/*
* We was helped by the RangeSlider code of ernieyu (https://github.com/ernieyu/Swing-range-slider)
* After several attempts and wrong way taken, we understood how all worked and we tried to do it ourself
*/

package ihm.rangeslider;

import javax.swing.*;

public class RangeSlider extends JSlider implements _RangeSlider {
    public RangeSlider(int min, int max, int value) {
        super(HORIZONTAL, min, max, value);
    }

    @Override
    public int getValueUpper() {
        return 0;
    }

    @Override
    public void setValueUpper(int upper) {

    }

    @Override
    public boolean checkValues() {
        return false;
    }
}
