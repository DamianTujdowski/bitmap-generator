package linearGradientGenerators;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.DoubleStream;

public abstract class LinearGradientGenerator implements ImageGenerator {
    protected final int width;
    protected final int height;
    protected final BufferedImage image;
    //TODO move RGB to another class?
    protected int red;
    protected int green;
    protected int blue;

    public LinearGradientGenerator(BufferedImage image, int r, int g, int b) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        red = r;
        green = g;
        blue = b;
    }

    private void generateVerticalLinearGradient() {
        int interval = height / (255 - blue);
        int tempInterval = interval;
        for (int y = 0; y < height; y++) {
            if (tempInterval > 0) {
                tempInterval--;
            } else if (tempInterval == 0) {
                blue++;
                tempInterval = interval;
            }

            int pixel = (red << 16) | (green << 8) | blue;

            for (int x = 0; x < width; x++) {
                image.setRGB(x, y, pixel);
            }
        }
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
        int steps = 255 - color;
        return steps < direction ? steps + 1 : direction;
    }

    public double computeInterval(int color, int direction) {
        int gradientSteps = 255 - color;
        double interval = (double) direction / (gradientSteps + 1);
        return gradientSteps > direction ? 1 : interval;
    }

    public int computeKeyToGradientValue(double interval, int y) {
        return y - (int) Math.round(y % interval);
    }

}
