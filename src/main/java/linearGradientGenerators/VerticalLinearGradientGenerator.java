package linearGradientGenerators;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VerticalLinearGradientGenerator implements ImageGenerator {

    private final LinearGradientGenerator generator;
    private final BufferedImage image;
    private final int width;
    private final int height;

    public VerticalLinearGradientGenerator(LinearGradientGenerator generator, BufferedImage image) {
        this.generator = generator;
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    @Override
    public void fillImage() {
        fillGradientsWithColorValues();
        double redInterval = generator.computeInterval(generator.red, height);
        double greenInterval = generator.computeInterval(generator.green, height);
        double blueInterval = generator.computeInterval(generator.blue, height);

        for (int y = 0; y < height; y++) {
            int redKey = generator.computeKeyToGradientValue(redInterval, y);
            int red = generator.getColorValue(generator.redGradientValues, redKey);

            int greenKey = generator.computeKeyToGradientValue(greenInterval, y);
            int green = generator.getColorValue(generator.greenGradientValues, greenKey);

            int blueKey = generator.computeKeyToGradientValue(blueInterval, y);
            int blue = generator.getColorValue(generator.blueGradientValues, blueKey);

            int pixel = (red << 16) | (green << 8) | blue;

            for (int x = 0; x < width; x++) {
                image.setRGB(x, y, pixel);
            }
        }
    }

    private void fillGradientsWithColorValues() {
        generator.fillGradientValues(generator.red, height, generator.redGradientValues);
        generator.fillGradientValues(generator.green, height, generator.greenGradientValues);
        generator.fillGradientValues(generator.blue, height, generator.blueGradientValues);
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
