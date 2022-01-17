import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class LinearGradientGeneratorTest {
    LinearGradientGenerator generator;
    BufferedImage image;

    @Test
    void checkBlueHorizontalGradientValues() {
        image = new BufferedImage(1000, 200, BufferedImage.TYPE_INT_BGR);
        generator = new LinearGradientGenerator(image, 255, 20, 125);
        generator.fillBlueHorizontalGradientValues();
        generator.blueHorizontalGradientValues.forEach((k,v) -> System.out.println(k + " : " + v));
    }

    @Test
    void checkComputeHorizontalInterval() {
        image = new BufferedImage(500, 200, BufferedImage.TYPE_INT_BGR);
        generator = new LinearGradientGenerator(image, 255, 20, 125);
        double interval = generator.computeHorizontalInterval(125);
        System.out.println(interval);
    }

    @Test
    void checkColorValueGain() {
        image = new BufferedImage(300, 100, BufferedImage.TYPE_INT_BGR);
        generator = new LinearGradientGenerator(image, 255, 20, 125);
        generator.generateImage();
        for (int i = 0; i < image.getWidth(); i++) {
            int res = image.getRGB(i, 0);
            Color color = new Color(res);
            System.out.println(color.getBlue());
        }
    }

    @Test
    void blueColorValue_shouldIncreaseByOne() {
        image = new BufferedImage(300, 100, BufferedImage.TYPE_INT_BGR);
        generator = new LinearGradientGenerator(image, 255, 20, 125);
        generator.generateImage();

        int numberOfStepsWithIncreaseValueBiggerThanOne = IntStream.range(0, image.getWidth())
                .mapToObj(i -> new Color(image.getRGB(i, 0)))
                .mapToInt(Color::getBlue)
                .reduce(this::calculateColorIncreaseValue)
                .getAsInt();

        assertEquals(0, numberOfStepsWithIncreaseValueBiggerThanOne);
    }

    private int calculateColorIncreaseValue(int f, int l) {
        if (!(f == l || f == l - 1)) {
            System.out.println(f + " : " + l);
        }
        return f == l || f == l - 1 ? 0 : 1;
    }

}