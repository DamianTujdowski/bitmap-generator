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

        for (int y = 0; y < width; y++) {
            int redKey = computeKeyToGradientValue(redInterval, y);
            red = redGradientValues.get(redKey);
            int greenKey = computeKeyToGradientValue(greenInterval, y);
            green = greenGradientValues.get(greenKey);
            int blueKey = computeKeyToGradientValue(blueInterval, y);
            blue = blueGradientValues.get(blueKey);

            int pixel = (red << 16) | (green << 8) | blue;

            for (int x = 0; x < height; x++) {
                image.setRGB(y, x, pixel);
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
