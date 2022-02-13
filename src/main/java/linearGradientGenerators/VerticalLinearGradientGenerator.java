package linearGradientGenerators;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

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

        System.out.println("generatorInterval " + redInterval);

        redGradientValues.entrySet()
                .stream()
                .filter(e -> e.getKey() > 90 & e.getKey() < 118)
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));

        for (int y = 0; y < height; y++) {
            int redKey = computeKeyToGradientValue(redInterval, y);

            if (redKey > 107) {
                System.out.println("redkey: " + redKey);
            }

//            red = redGradientValues.get(redKey);
            red = getValue(redGradientValues, redKey);
            int greenKey = computeKeyToGradientValue(greenInterval, y);
            green = greenGradientValues.get(greenKey);
            int blueKey = computeKeyToGradientValue(blueInterval, y);
            blue = blueGradientValues.get(blueKey);

            int pixel = (red << 16) | (green << 8) | blue;

            for (int x = 0; x < width; x++) {
                image.setRGB(x, y, pixel);
            }
        }
    }

    private int getValue(Map<Integer, Integer> gradientColorValues, int key) {
        if (gradientColorValues.containsKey(key)) {
            return gradientColorValues.get(key);
        } else {
            return gradientColorValues.get(++key);
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
