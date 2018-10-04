/*
 * We were helped by the RangeSlider code of ernieyu (https://github.com/ernieyu/Swing-range-slider)
 * After several attempts and wrong way taken, we understood how all worked and we tried to do it ourself
 */

package ihm.rangeslider;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class RangeSliderUI extends BasicSliderUI {

    private Rectangle upperThumbRect;

    private boolean upperThumbSelected;

    private transient boolean lowerDragging;
    private transient boolean upperDragging;
    private transient boolean trackDragging;

    RangeSliderUI(RangeSlider b) {
        super(b);
    }

    @Override
    public void installUI(JComponent c) {
        upperThumbRect = new Rectangle();
        super.installUI(c);
    }

    @Override
    protected TrackListener createTrackListener(JSlider slider) {
        return new RangeTrackListener();
    }

    @Override
    protected ChangeListener createChangeListener(JSlider slider) {
        return new ChangeHandler();
    }

    @Override
    protected void calculateThumbSize() {
        super.calculateThumbSize();
        upperThumbRect.setSize(thumbRect.width, thumbRect.height);
    }

    @Override
    protected void calculateThumbLocation() {
        super.calculateThumbLocation();

        if (slider.getSnapToTicks()) {
            int upperValue = slider.getValue() + slider.getExtent();
            int snappedValue = upperValue;
            int majorTickSpacing = slider.getMajorTickSpacing();
            int minorTickSpacing = slider.getMinorTickSpacing();
            int tickSpacing = 0;

            if (minorTickSpacing > 0) {
                tickSpacing = minorTickSpacing;
            } else if (majorTickSpacing > 0) {
                tickSpacing = majorTickSpacing;
            }

            if (tickSpacing != 0) {
                if ((upperValue - slider.getMinimum()) % tickSpacing != 0) {
                    float temp = (float) (upperValue - slider.getMinimum()) / (float) tickSpacing;
                    int whichTick = Math.round(temp);
                    snappedValue = slider.getMinimum() + (whichTick * tickSpacing);
                }

                if (snappedValue != upperValue) {
                    slider.setExtent(snappedValue - slider.getValue());
                }
            }
        }

        if (slider.getOrientation() == JSlider.HORIZONTAL) {
            int upperPosition = xPositionForValue(slider.getValue() + slider.getExtent());
            upperThumbRect.x = upperPosition - (upperThumbRect.width / 2);
            upperThumbRect.y = trackRect.y;

        } else {
            int upperPosition = yPositionForValue(slider.getValue() + slider.getExtent());
            upperThumbRect.x = trackRect.x;
            upperThumbRect.y = upperPosition - (upperThumbRect.height / 2);
        }
    }

    @Override
    protected Dimension getThumbSize() {
        return new Dimension(12, 12);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        Rectangle clipRect = g.getClipBounds();
        if (upperThumbSelected) {
            if (clipRect.intersects(thumbRect)) {
                paintLowerThumb(g);
            }
            if (clipRect.intersects(upperThumbRect)) {
                paintUpperThumb(g);
            }

        } else {
            if (clipRect.intersects(upperThumbRect)) {
                paintUpperThumb(g);
            }
            if (clipRect.intersects(thumbRect)) {
                paintLowerThumb(g);
            }
        }
    }

    @Override
    public void paintThumb(Graphics g) {

    }

    @Override
    public void paintTrack(Graphics g) {
        super.paintTrack(g);

        Rectangle trackBounds = trackRect;

        int lowerX = thumbRect.x + (thumbRect.width / 2);
        int upperX = upperThumbRect.x + (upperThumbRect.width / 2);

        int cy = (trackBounds.height / 2) - 2;

        Color oldColor = g.getColor();
        g.translate(trackBounds.x, trackBounds.y + cy);

        g.setColor(new Color(244, 220, 107));
        for (int y = 0; y <= 3; y++) {
            g.drawLine(lowerX - trackBounds.x, y, upperX - trackBounds.x, y);
        }

        g.translate(-trackBounds.x, -(trackBounds.y + cy));
        g.setColor(oldColor);
    }

    private void paintLowerThumb(Graphics g) {
        Rectangle knobBounds = thumbRect;
        int w = knobBounds.width;
        int h = knobBounds.height;

        Graphics2D g2d = (Graphics2D) g.create();

        Shape thumbShape = createThumbShape(w - 1, h - 1);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(knobBounds.x, knobBounds.y);

        g2d.setColor(new Color(220, 142, 149));
        g2d.fill(thumbShape);

        g2d.dispose();
    }

    private void paintUpperThumb(Graphics g) {
        Rectangle knobBounds = upperThumbRect;
        int w = knobBounds.width;
        int h = knobBounds.height;

        Graphics2D g2d = (Graphics2D) g.create();

        Shape thumbShape = createThumbShape(w - 1, h - 1);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(knobBounds.x, knobBounds.y);

        g2d.setColor(new Color(96, 161, 164));
        g2d.fill(thumbShape);

        g2d.dispose();
    }

    private Shape createThumbShape(int width, int height) {
        return new Ellipse2D.Double(0, 0, width, height);
    }

    private void setUpperThumbLocation(int x, int y) {
        Rectangle upperUnionRect = new Rectangle();
        upperUnionRect.setBounds(upperThumbRect);

        upperThumbRect.setLocation(x, y);

        SwingUtilities.computeUnion(upperThumbRect.x, upperThumbRect.y, upperThumbRect.width, upperThumbRect.height, upperUnionRect);
        slider.repaint(upperUnionRect.x, upperUnionRect.y, upperUnionRect.width, upperUnionRect.height);
    }

    public class RangeTrackListener extends TrackListener {

        @Override
        public void mousePressed(MouseEvent e) {
            if (!slider.isEnabled()) {
                return;
            }

            currentMouseX = e.getX();
            currentMouseY = e.getY();

            if (slider.isRequestFocusEnabled()) {
                slider.requestFocus();
            }

            boolean trackpressed = false;
            boolean lowerPressed = false;
            boolean upperPressed = false;
            if (upperThumbSelected || slider.getMinimum() == slider.getValue()) {
                if (upperThumbRect.contains(currentMouseX, currentMouseY)) {
                    upperPressed = true;
                } else if (thumbRect.contains(currentMouseX, currentMouseY)) {
                    lowerPressed = true;
                }
            } else {
                if (thumbRect.contains(currentMouseX, currentMouseY)) {
                    lowerPressed = true;
                } else if (upperThumbRect.contains(currentMouseX, currentMouseY)) {
                    upperPressed = true;
                } else if (trackRect.contains(currentMouseX, currentMouseY)) {
                    trackpressed = true;
                }
            }

            if (lowerPressed) {
                offset = currentMouseX - thumbRect.x;
                upperThumbSelected = false;
                lowerDragging = true;
                return;
            }
            lowerDragging = false;

            if (upperPressed) {
                offset = currentMouseX - upperThumbRect.x;
                upperThumbSelected = true;
                upperDragging = true;
                return;
            }
            upperDragging = false;

            if (trackpressed) {
                offset = currentMouseX - trackRect.x;
                trackDragging = true;
                return;
            }
            trackDragging = false;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            lowerDragging = false;
            upperDragging = false;
            trackDragging = false;
            slider.setValueIsAdjusting(false);
            super.mouseReleased(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (!slider.isEnabled()) {
                return;
            }

            currentMouseX = e.getX();
            currentMouseY = e.getY();

            if (trackDragging) {
                slider.setValueIsAdjusting(true);
                moveTrackThumb();
            } else if (lowerDragging) {
                slider.setValueIsAdjusting(true);
                moveLowerThumb();
            } else if (upperDragging) {
                slider.setValueIsAdjusting(true);
                moveUpperThumb();
            }
        }

        @Override
        public boolean shouldScroll(int direction) {
            return false;
        }

        private void moveLowerThumb() {
            int thumbMiddle;

            int halfThumbWidth = thumbRect.width / 2;
            int thumbLeft = currentMouseX - offset;
            int trackLeft = trackRect.x;
            int trackRight = xPositionForValue(slider.getValue() + slider.getExtent());

            thumbLeft = Math.max(thumbLeft, trackLeft - halfThumbWidth);
            thumbLeft = Math.min(thumbLeft, trackRight - halfThumbWidth);

            setThumbLocation(thumbLeft, thumbRect.y);

            thumbMiddle = thumbLeft + halfThumbWidth;
            slider.setValue(valueForXPosition(thumbMiddle));
        }

        private void moveUpperThumb() {
            int thumbMiddle;

            int halfThumbWidth = thumbRect.width / 2;
            int thumbLeft = currentMouseX - offset;
            int trackLeft = xPositionForValue(slider.getValue());
            int trackRight = trackRect.x + (trackRect.width - 1);

            thumbLeft = Math.max(thumbLeft, trackLeft - halfThumbWidth);
            thumbLeft = Math.min(thumbLeft, trackRight - halfThumbWidth);

            setUpperThumbLocation(thumbLeft, thumbRect.y);

            thumbMiddle = thumbLeft + halfThumbWidth;
            slider.setExtent(valueForXPosition(thumbMiddle) - slider.getValue());
        }

        private void moveTrackThumb() {
            /** TODO
             * We tried many things, we guess we don't have understand a detail of how it works
             */
        }
    }
}