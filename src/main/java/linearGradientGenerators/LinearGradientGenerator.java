package linearGradientGenerators;

import constants.RGBColorValues;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.DoubleStream;

public class LinearGradientGenerator {
    protected Map<Integer, Integer> redGradientValues;
    protected Map<Integer, Integer> greenGradientValues;
    protected Map<Integer, Integer> blueGradientValues;
    private final Color startColor;
    private final Color endColor;
    protected int red;
    protected int green;
    protected int blue;
//TODO add method/class that generates colors based on number of passed arguments
    //0 random
    //1 1 random color 1 user
    //2 0 random colors, both from user
    public LinearGradientGenerator(Color ...colors) {
        redGradientValues = new LinkedHashMap<>();
        greenGradientValues = new LinkedHashMap<>();
        blueGradientValues = new LinkedHashMap<>();
        //TODO implement method
        startColor = computeColorRGBValues(colors);
        endColor = computeColorRGBValues(colors);
    }

    private Color computeColorRGBValues(Color[] colors) {
        int count = colors.length;

        return new Color(13,13,13);
    }
//TODO replace color variable with Enum indicating color
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

    //TODO replace color variable with Enum indicating color
    private int computeStepsLimit(int color, int direction) {
        int gradientSteps = RGBColorValues.MAXIMUM_VALUE - color;
        return gradientSteps < direction ? gradientSteps + 1 : direction;
    }

    //TODO gradientSteps = startColor.getRed() - endColor.getRed()  color variable with Enum indicating color
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
