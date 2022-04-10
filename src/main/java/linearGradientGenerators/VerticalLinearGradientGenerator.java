package linearGradientGenerators;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VerticalLinearGradientGenerator implements ImageGenerator {

    private final LinearGradientGenerator generator;
    private final BufferedImage image;
    private final int width;
    private final int height;

    public VerticalLinearGradientGenerator(BufferedImage image, Color... colors) {
        this.generator = new LinearGradientGenerator(image.getHeight(), colors);
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    @Override
    public void fillImage() {
        double redInterval = generator.computeRedInterval();
        double greenInterval = generator.computeGreenInterval();
        double blueInterval = generator.computeBlueInterval();

        for (int y = 0; y < height; y++) {
            int redKey = generator.computeKeyToGradientValue(redInterval, y);
            int red = generator.getRedGradientColorValue(redKey);

            int greenKey = generator.computeKeyToGradientValue(greenInterval, y);
            int green = generator.getGreenGradientColorValue(greenKey);

            int blueKey = generator.computeKeyToGradientValue(blueInterval, y);
            int blue = generator.getBlueGradientColorValue(blueKey);

            int pixel = (red << 16) | (green << 8) | blue;

            for (int x = 0; x < width; x++) {
                image.setRGB(x, y, pixel);
            }
        }
    }

    @Override
    public void saveImage(String fileFormat, String path) {
        try {
            File file = new File(path);
            ImageIO.write(image, fileFormat, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
