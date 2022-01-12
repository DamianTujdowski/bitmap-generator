import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class LinearGradientGenerator implements ImageGenerator {
    public final BufferedImage image;
    public final int width;
    public final int height;
    public Map<Integer, Integer> blueHorizontalGradientValues;
    //TODO move RGB to another class?
    public int red;
    public int green;
    public int blue;

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

    public void generateVerticalLinearGradient() {
        int red = 255, blue = 0, green = 124;
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

    public void generateHorizontalLinearGradient() {
        fillBlueHorizontalGradientValues();

        int blueInterval = computeHorizontalInterval(blue);

        for (int y = 0; y < width; y++) {
            int key = y - y % blueInterval;
            blue = blueHorizontalGradientValues.get(key);

            int pixel = (red << 16) | (green << 8) | blue;

            for (int x = 0; x < height; x++) {
                image.setRGB(y, x, pixel);
            }
        }
    }

    public void fillBlueHorizontalGradientValues() {
        int interval = computeHorizontalInterval(blue);
        final int[] tempBlue = {blue};

        IntStream.iterate(0, n -> n + interval)
                .limit(255 - blue)
                .forEach(num -> blueHorizontalGradientValues.put(num, tempBlue[0]++));
    }

    public int computeHorizontalInterval(int color) {
        return width / (255 - color);
    }

    @Override
    public void saveImage() {
        try {
            File file = new File("src\\main\\java\\pics\\linearGradient.jpeg");
            ImageIO.write(image, "jpeg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
