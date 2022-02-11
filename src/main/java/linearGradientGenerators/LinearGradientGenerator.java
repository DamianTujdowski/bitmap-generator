package linearGradientGenerators;

import constants.RGBColorValues;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.stream.DoubleStream;

public abstract class LinearGradientGenerator implements ImageGenerator {
    protected final int width;
    protected final int height;
    protected final BufferedImage image;
    protected int red;
    protected int green;
    protected int blue;

    public LinearGradientGenerator(BufferedImage image, Color startColor) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        red = startColor.getRed();
        green = startColor.getGreen();
        blue = startColor.getBlue();
    }

    public void fillGradientValues(int color, int direction, Map<Integer, Integer> gradientValues) {
        double interval = computeInterval(color, direction);
        final int[] tempBlue = {color};
        int stepsLimit = computeStepsLimit(color, direction);
        DoubleStream.iterate(0, n -> n + interval)
                .limit(stepsLimit)
                .forEach(num ->
                        gradientValues.put((int) Math.round(num), tempBlue[0]++)
                );
    }

    public int computeStepsLimit(int color, int direction) {
        int steps = RGBColorValues.MAXIMUM_VALUE - color;
        return steps < direction ? steps + 1 : direction;
    }

    public double computeInterval(int color, int direction) {
        int gradientSteps = RGBColorValues.MAXIMUM_VALUE - color;
        double interval = (double) direction / (gradientSteps + 1);
        return gradientSteps > direction ? 1 : interval;
    }

    public int computeKeyToGradientValue(double interval, int y) {
        return y - (int) Math.round(y % interval);
    }

}
