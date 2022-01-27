package linearGradientGenerators;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class VerticalLinearGradientGenerator extends LinearGradientGenerator {
    private final Map<Integer, Integer> blueGradientValues;

    public VerticalLinearGradientGenerator(BufferedImage image, int r, int g, int b) {
        super(image, r, g, b);
        blueGradientValues = new LinkedHashMap<>();
    }

    @Override
    public void generateImage() {
        fillGradientValues(blue, height, blueGradientValues);
        double blueInterval = computeInterval(blue, height);

        for (int y = 0; y < height; y++) {
            int key = computeKeyToGradientValue(blueInterval, y);
            blue = blueGradientValues.get(key);

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
