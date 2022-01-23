package linearGradientGenerators;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class HorizontalLinearGradientGenerator extends LinearGradientGenerator{
    private final Map<Integer, Integer> blueGradientValues;

    public HorizontalLinearGradientGenerator(BufferedImage image, int r, int g, int b) {
        super(image, r, g, b);
        blueGradientValues = new LinkedHashMap<>();
    }

    @Override
    public void generateImage() {
        fillGradientValues(blue, width, blueGradientValues);
        double blueInterval = computeInterval(blue, width);

        for (int y = 0; y < width; y++) {
            int key = computeKeyToGradientValue(blueInterval, y);
            blue = blueGradientValues.get(key);

            int pixel = (red << 16) | (green << 8) | blue;

            for (int x = 0; x < height; x++) {
                image.setRGB(y, x, pixel);
            }
        }

    }

    @Override
    public void saveImage() {
        try {
            File file = new File("src\\main\\resources\\pics\\linearGradient.png");
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
