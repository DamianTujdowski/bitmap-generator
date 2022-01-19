import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class LinearGradientGeneratorTest {
    LinearGradientGenerator generator;
    BufferedImage image;

    @Test
    void printColorValueGain() {
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
    void printBlueHorizontalGradientValues() {
        image = new BufferedImage(1000, 200, BufferedImage.TYPE_INT_BGR);
        generator = new LinearGradientGenerator(image, 255, 20, 125);
        generator.fillHorizontalGradientValues(125, generator.blueHorizontalGradientValues);
        generator.blueHorizontalGradientValues.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    @Test
    void HorizontalInterval_shouldBe3_8() {
        image = new BufferedImage(500, 200, BufferedImage.TYPE_INT_BGR);
        generator = new LinearGradientGenerator(image, 255, 20, 125);
        double interval = generator.computeHorizontalInterval(125);
        assertEquals(3.816793893129771, interval);
    }

    @Test
    void blueColorValuesMapLength_shouldBe50() {
        image = new BufferedImage(50, 100, BufferedImage.TYPE_INT_BGR);
        generator = new LinearGradientGenerator(image, 255, 20, 125);
        generator.generateImage();
        int blueGradientValuesMapLength = generator.blueHorizontalGradientValues.size();
        assertEquals(50, blueGradientValuesMapLength);
    }

    @Test
    void blueColorValuesMapLength_shouldB131() {
        image = new BufferedImage(500, 100, BufferedImage.TYPE_INT_BGR);
        generator = new LinearGradientGenerator(image, 255, 20, 125);
        generator.generateImage();
        int blueGradientValuesMapLength = generator.blueHorizontalGradientValues.size();
        assertEquals(131, blueGradientValuesMapLength);
    }

    @Test
    void blueColorValue_shouldIncreaseByOne() {
        image = new BufferedImage(300, 100, BufferedImage.TYPE_INT_BGR);
        generator = new LinearGradientGenerator(image, 255, 20, 125);
        generator.generateImage();

        int numberOfStepsWithIncreaseValueBiggerThanOne = IntStream.range(0, image.getWidth())
                .mapToObj(i -> new Color(image.getRGB(i, 0)))
                .mapToInt(Color::getBlue)
                .reduce(this::checkColorIncreaseValue)
                .getAsInt() % 255;

        assertEquals(0, numberOfStepsWithIncreaseValueBiggerThanOne);
    }

    private int checkColorIncreaseValue(int f, int l) {
        if (!(f == l || f == l - 1)) {
            System.out.println(f + " : " + l);
        }
        return  f == l || f == l - 1 ? l : 0;
    }

}