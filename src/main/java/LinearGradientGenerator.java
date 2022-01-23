import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.DoubleStream;

public class LinearGradientGenerator implements ImageGenerator {
    private final BufferedImage image;
    private final int width;
    private final int height;
    private final Map<Integer, Integer> blueHorizontalGradientValues;
    //TODO move RGB to another class?
    private int red;
    private int green;
    private int blue;

    public LinearGradientGenerator(BufferedImage image, int r, int g, int b) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        blueHorizontalGradientValues = new LinkedHashMap<>();
        red = r;
        green = g;
        blue = b;
    }

    @Override
    public void generateImage() {
        generateHorizontalLinearGradient();
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

    private void generateHorizontalLinearGradient() {
        fillGradientValues(blue, width, blueHorizontalGradientValues);
        double blueInterval = computeInterval(blue, width);

        for (int y = 0; y < width; y++) {
            int key = computeKeyToGradientValue(blueInterval, y);
            blue = blueHorizontalGradientValues.get(key);

            int pixel = (red << 16) | (green << 8) | blue;

            for (int x = 0; x < height; x++) {
                image.setRGB(y, x, pixel);
            }
        }
    }

    private void fillGradientValues(int color, int direction, Map<Integer, Integer> gradientValues) {
        double interval = computeInterval(color, direction);
        final int[] tempBlue = {color};
        int stepsLimit = computeStepsLimit(color, direction);
        DoubleStream.iterate(0, n -> n + interval)
                .limit(stepsLimit)
                .forEach(num ->
                        gradientValues.put((int) Math.round(num), tempBlue[0]++)
                );
    }

    private int computeStepsLimit(int color, int direction) {
        int steps = 255 - color;
        return steps < direction ? steps + 1 : direction;
    }

    private double computeInterval(int color, int direction) {
        int gradientSteps = 255 - color;
        double interval = (double) direction / (gradientSteps + 1);
        return gradientSteps > direction ? 1 : interval;
    }

    private int computeKeyToGradientValue(double interval, int y) {
        return y - (int) Math.round(y % interval);
    }

    @Override
    public void saveImage() {
        try {
            File file = new File("src\\main\\resources\\pics\\linearGradient.png");
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
