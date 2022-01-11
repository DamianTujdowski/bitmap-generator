import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LinearGradientGenerator implements ImageGenerator {
    private final BufferedImage image;
    private final int width;
    private final int height;

    public LinearGradientGenerator(BufferedImage image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
    }


    @Override
    public void generateImage() {
        generateHorizontalLinearGradient();
    }

    private void generateVerticalLinearGradient() {
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

    private void generateHorizontalLinearGradient() {
        int red = 255, blue = 20, green = 124;
        int interval = width / (255 - blue);
        int tempInterval = interval;
        for (int y = 0; y < width; y++) {
            if (tempInterval > 0) {
                tempInterval--;
            } else if (tempInterval == 0) {
                blue++;
                tempInterval = interval;
            }

            int pixel = (red << 16) | (green << 8) | blue;

            for (int x = 0; x < height; x++) {
                image.setRGB(y, x, pixel);
            }
        }
    }

    @Override
    public void saveImage() {
        try {
            File file = new File("src\\pics\\linearGradient.jpeg");
            ImageIO.write(image, "jpeg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
