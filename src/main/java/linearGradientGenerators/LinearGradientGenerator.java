package linearGradientGenerators;

import constants.RGBColorValues;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.DoubleStream;

public abstract class LinearGradientGenerator implements ImageGenerator {
    protected final int width;
    protected final int height;
    protected final BufferedImage image;
    protected final Map<Integer, Integer> redGradientValues;
    protected final Map<Integer, Integer> greenGradientValues;
    protected final Map<Integer, Integer> blueGradientValues;
    protected int red;
    protected int green;
    protected int blue;

    public LinearGradientGenerator(BufferedImage image, Color startColor) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        redGradientValues = new LinkedHashMap<>();
        greenGradientValues = new LinkedHashMap<>();
        blueGradientValues = new LinkedHashMap<>();
        red = startColor.getRed();
        green = startColor.getGreen();
        blue = startColor.getBlue();
    }

    public void fillGradientValues(int color, int direction, Map<Integer, Integer> gradientValues) {
        double interval = computeInterval(color, direction);
        System.out.println("fill interval " + interval);
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
        if (interval > 5 & y > 100) {
            System.out.println(interval + ": " + y);
            System.out.println((int) Math.round(y % interval));
            System.out.println( y - (int) Math.round(y % interval));
        }
        return y - (int) Math.round(y % interval);
    }

}
