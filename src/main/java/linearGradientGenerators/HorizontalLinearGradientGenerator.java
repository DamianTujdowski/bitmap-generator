package linearGradientGenerators;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HorizontalLinearGradientGenerator extends LinearGradientGenerator{

    public HorizontalLinearGradientGenerator(BufferedImage image, Color startColor) {
        super(image, startColor);
    }

    @Override
    public void generateImage() {
        fillGradientsWithColorValues();
        double redInterval = computeInterval(red, width);
        double greenInterval = computeInterval(green, width);
        double blueInterval = computeInterval(blue, width);

        for (int x = 0; x < width; x++) {
            int redKey = computeKeyToGradientValue(redInterval, x);
            red = getColorValue(redGradientValues, redKey);

            int greenKey = computeKeyToGradientValue(greenInterval, x);
            green = getColorValue(greenGradientValues, greenKey);

            int blueKey = computeKeyToGradientValue(blueInterval, x);
            blue = getColorValue(blueGradientValues, blueKey);

            int pixel = (red << 16) | (green << 8) | blue;

            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, pixel);
            }
        }

    }

    private void fillGradientsWithColorValues() {
        fillGradientValues(red, width, redGradientValues);
        fillGradientValues(green, width, greenGradientValues);
        fillGradientValues(blue, width, blueGradientValues);
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
