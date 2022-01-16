import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.DoubleStream;

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
        double blueInterval = computeHorizontalInterval(blue);

        for (int y = 0; y < width; y++) {
            int key = calculateKeyForBlue(blueInterval, y);
            blue = blueHorizontalGradientValues.get(key);

            int pixel = (red << 16) | (green << 8) | blue;

            for (int x = 0; x < height; x++) {
                image.setRGB(y, x, pixel);
            }
        }
    }

    private int calculateKeyForBlue(double blueInterval, int y) {

//        int key = y - (int) Math.round(y % blueInterval);
        return y - (int) Math.round(y % blueInterval);
    }


    public void fillBlueHorizontalGradientValues() {
        double interval = computeHorizontalInterval(blue);
        int temp = blue;
        final int[] tempBlue = {temp};

        DoubleStream.iterate(0, n -> n + interval)
                .limit(255 - blue + 1)
                .forEach(num -> blueHorizontalGradientValues.put((int) Math.round(num), tempBlue[0]++));
    }

    public double computeHorizontalInterval(int color) {
        if (255 - color > width) {
            return 1;
        }
        return (double) width / (255 - color + 1);
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
