package linearGradientGenerators;

import java.awt.*;

public class LinearGradientGenerator {
    private final RandomColorGenerator generator;

    private final Color startColor;
    private final Color endColor;
    private ColorGradient redGradient;
    private ColorGradient greenGradient;
    private ColorGradient blueGradient;
    private final int dimension;

    public LinearGradientGenerator(int dimension, Color... userColors) {
        generator = new RandomColorGenerator();
        this.dimension = dimension;
        Color[] colors = computeColorRGBValues(userColors);
        startColor = colors[0];
        endColor = colors[1];
        redGradient = new ColorGradient(startColor.getRed(), endColor.getRed());
        greenGradient = new ColorGradient(startColor.getGreen(), endColor.getGreen());
        blueGradient = new ColorGradient(startColor.getBlue(), endColor.getBlue());
        fillColorGradientsWithColorValues();
    }

    private Color[] computeColorRGBValues(Color[] colors) {
        Color[] result = new Color[2];
        int numberOfPassedColors = colors.length;
        switch (numberOfPassedColors) {
            case 0:
                result[0] = generator.generateStartColor();
                result[1] = generator.generateEndColor(result[0]);
            case 1:
                result[0] = colors[0];
                result[1] = generator.generateEndColor(result[0]);
                break;
            default:
                result[0] = colors[0];
                result[1] = colors[1];
                break;
        }
        return result;
    }

    private void fillColorGradientsWithColorValues() {
        redGradient.fillGradientValues(dimension);
        greenGradient.fillGradientValues(dimension);
        blueGradient.fillGradientValues(dimension);
    }

    public double computeRedInterval() {
        return redGradient.computeInterval(dimension);
    }

    public double computeGreenInterval() {
        return greenGradient.computeInterval(dimension);
    }

    public double computeBlueInterval() {
        return blueGradient.computeInterval(dimension);
    }

    public int getRedGradientColorValue(int key) {
        return redGradient.getColorValue(key);
    }

    public int getGreenGradientColorValue(int key) {
        return greenGradient.getColorValue(key);
    }

    public int getBlueGradientColorValue(int key) {
        return blueGradient.getColorValue(key);
    }

    public int computeKeyToGradientValue(double interval, int coordinate) {
        return coordinate - (int) Math.round(coordinate % interval);
    }

}
