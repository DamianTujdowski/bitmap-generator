package linearGradientGenerators;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class HorizontalLinearGradientGenerator extends LinearGradientGenerator{
    private final Map<Integer, Integer> redGradientValues;
    private final Map<Integer, Integer> greenGradientValues;
    private final Map<Integer, Integer> blueGradientValues;

    public HorizontalLinearGradientGenerator(BufferedImage image, Color startColor) {
        super(image, startColor);
        redGradientValues = new LinkedHashMap<>();
        greenGradientValues = new LinkedHashMap<>();
        blueGradientValues = new LinkedHashMap<>();
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
