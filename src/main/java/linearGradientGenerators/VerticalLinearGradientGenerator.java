package linearGradientGenerators;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VerticalLinearGradientGenerator extends LinearGradientGenerator {

    public VerticalLinearGradientGenerator(BufferedImage image, Color startColor) {
        super(image, startColor);
    }

    @Override
    public void generateImage() {
        fillGradientsWithColorValues();
        double redInterval = computeInterval(red, height);
        double greenInterval = computeInterval(green, height);
        double blueInterval = computeInterval(blue, height);

        for (int y = 0; y < height; y++) {
            int redKey = computeKeyToGradientValue(redInterval, y);
            red = getColorValue(redGradientValues, redKey);

            int greenKey = computeKeyToGradientValue(greenInterval, y);
            green = getColorValue(greenGradientValues, greenKey);

            int blueKey = computeKeyToGradientValue(blueInterval, y);
            blue = getColorValue(blueGradientValues, blueKey);

            int pixel = (red << 16) | (green << 8) | blue;

            for (int x = 0; x < width; x++) {
                image.setRGB(x, y, pixel);
            }
        }
    }

    private void fillGradientsWithColorValues() {
        fillGradientValues(red, height, redGradientValues);
        fillGradientValues(green, height, greenGradientValues);
        fillGradientValues(blue, height, blueGradientValues);
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
