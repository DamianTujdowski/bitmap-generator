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
        final int[] tempColor = {color};
        int stepsLimit = computeStepsLimit(color, direction);

        DoubleStream.iterate(0, n -> n + interval)
                .limit(stepsLimit)
                .forEach(num ->
                        gradientValues.put((int) Math.round(num), tempColor[0]++)
                );
    }

    public int computeStepsLimit(int color, int direction) {
        int gradientSteps = RGBColorValues.MAXIMUM_VALUE - color;
        return gradientSteps < direction ? gradientSteps + 1 : direction;
    }

    public double computeInterval(int color, int direction) {
        int gradientSteps = RGBColorValues.MAXIMUM_VALUE - color;
        double interval = (double) direction / (gradientSteps + 1);
        return gradientSteps > direction ? 1 : interval;
    }

    public int computeKeyToGradientValue(double interval, int coordinate) {
        return coordinate - (int) Math.round(coordinate % interval);
    }

    public int getColorValue(Map<Integer, Integer> gradientColorValues, int key) {
        if (gradientColorValues.containsKey(key)) {
            return gradientColorValues.get(key);
        } else {
            return gradientColorValues.get(++key);
        }
    }

}
