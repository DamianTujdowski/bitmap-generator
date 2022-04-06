package linearGradientGenerators;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HorizontalLinearGradientGenerator implements ImageGenerator {

    private final LinearGradientGenerator generator;
    private final BufferedImage image;
    private final int width;
    private final int height;

    public HorizontalLinearGradientGenerator(LinearGradientGenerator generator, BufferedImage image) {
        this.generator = generator;
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    @Override
    public void fillImage() {
        fillGradientsWithColorValues();
        double redInterval = generator.computeInterval(generator.red, width);
        double greenInterval = generator.computeInterval(generator.green, width);
        double blueInterval = generator.computeInterval(generator.blue, width);


        for (int x = 0; x < width; x++) {
            int redKey = generator.computeKeyToGradientValue(redInterval, x);
            int red = generator.getColorValue(generator.redGradientValues, redKey);

            int greenKey = generator.computeKeyToGradientValue(greenInterval, x);
            int green = generator.getColorValue(generator.greenGradientValues, greenKey);

            int blueKey = generator.computeKeyToGradientValue(blueInterval, x);
            int blue = generator.getColorValue(generator.blueGradientValues, blueKey);

            int pixel = (red << 16) | (green << 8) | blue;

            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, pixel);
            }
        }

    }

    private void fillGradientsWithColorValues() {
        generator.fillGradientValues(generator.red, width, generator.redGradientValues);
        generator.fillGradientValues(generator.green, width, generator.greenGradientValues);
        generator.fillGradientValues(generator.blue, width, generator.blueGradientValues);
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
