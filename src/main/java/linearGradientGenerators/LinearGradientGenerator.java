package linearGradientGenerators;

import constants.ColorIndicator;
import constants.RGBColorValues;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.DoubleStream;

public class LinearGradientGenerator {
    private final RandomColorGenerator generator;

    protected Map<Integer, Integer> redGradientValues;
    protected Map<Integer, Integer> greenGradientValues;
    protected Map<Integer, Integer> blueGradientValues;
    private final Color startColor;
    private final Color endColor;

    public LinearGradientGenerator(Color... userColors) {
        generator = new RandomColorGenerator();
        redGradientValues = new LinkedHashMap<>();
        greenGradientValues = new LinkedHashMap<>();
        blueGradientValues = new LinkedHashMap<>();
        Color[] colors = computeColorRGBValues(userColors);
        startColor = colors[0];
        endColor = colors[1];
    }

    private Color[] computeColorRGBValues(Color[] colors) {
        Color[] result = new Color[2];
        int numberOfPassedColors = colors.length;
        switch (numberOfPassedColors) {
            case 1:
                result[0] = colors[0];
                result[1] = generator.generateEndColor(result[0]);
                break;
            case 2:
                result[0] = colors[0];
                result[1] = colors[1];
                break;
            default:
                result[0] = generator.generateStartColor();
                result[1] = generator.generateEndColor(result[0]);
                break;
        }
        return result;
    }

    //TODO fix logic in DoubleStream - what if startColor is smaller then endColor
    public void fillGradientValues(ColorIndicator indicator, int direction, Map<Integer, Integer> gradientValues) {
        double interval = computeInterval(indicator, direction);
        int startColor = computeStartColor(indicator);
        final int[] tempColor = {startColor};
        int stepsLimit = computeStepsLimit(indicator, direction);

        DoubleStream.iterate(0, n -> n + interval)
                .limit(stepsLimit)
                .forEach(num ->
                        gradientValues.put((int) Math.round(num), tempColor[0]++)
                );
    }

    //TODO improve name
    private int computeStartColor(ColorIndicator indicator) {
        switch (indicator) {
            case RED:
                return startColor.getRed();
            case GREEN:
                return startColor.getGreen();
            default:
                return startColor.getBlue();
        }
    }

    private int computeStepsLimit(ColorIndicator indicator, int direction) {
        int gradientSteps = computeGradientSteps(indicator);
        return gradientSteps < direction ? gradientSteps + 1 : direction;
    }

    public double computeInterval(ColorIndicator indicator, int direction) {
        int gradientSteps = computeGradientSteps(indicator);
        double interval = (double) direction / (gradientSteps + 1);
        return gradientSteps > direction ? 1 : interval;
    }

    private int computeGradientSteps(ColorIndicator indicator) {
        int result = 0;
        switch (indicator) {
            case RED:
                result = startColor.getRed() - endColor.getRed();
            case GREEN:
                result = startColor.getGreen() - endColor.getGreen();
            case BLUE:
                result = startColor.getBlue() - endColor.getBlue();
        }
        return Math.abs(result);
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
