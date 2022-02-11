import constants.RGBColorValues;
import linearGradientGenerators.HorizontalLinearGradientGenerator;
import linearGradientGenerators.LinearGradientGenerator;
import linearGradientGenerators.VerticalLinearGradientGenerator;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class VerticalLinearGradientGeneratorTest {
    LinearGradientGenerator generator;
    BufferedImage image;
    Color color;

    @Test
    void redColorValue_shouldIncreaseByOne() {
        image = new BufferedImage(100, 300, BufferedImage.TYPE_INT_BGR);
        color = new Color(200, 20, 125);
        generator = new VerticalLinearGradientGenerator(image, color);
        generator.generateImage();

        int numberOfStepsWithIncreaseValueBiggerThanOne = IntStream.range(0, image.getHeight())
                .mapToObj(i -> new Color(image.getRGB(0, i)))
                .mapToInt(Color::getRed)
                .reduce(this::checkColorIncreaseValue)
                .getAsInt() % RGBColorValues.MAXIMUM_VALUE;

        assertEquals(0, numberOfStepsWithIncreaseValueBiggerThanOne);
    }

    @Test
    void greenColorValue_shouldIncreaseByOne() {
        image = new BufferedImage(100, 300, BufferedImage.TYPE_INT_BGR);
        color = new Color(255, 20, 125);
        generator = new VerticalLinearGradientGenerator(image, color);
        generator.generateImage();

        int numberOfStepsWithIncreaseValueBiggerThanOne = IntStream.range(0, image.getHeight())
                .mapToObj(i -> new Color(image.getRGB(0, i)))
                .mapToInt(Color::getGreen)
                .reduce(this::checkColorIncreaseValue)
                .getAsInt() % RGBColorValues.MAXIMUM_VALUE;

        assertEquals(0, numberOfStepsWithIncreaseValueBiggerThanOne);
    }

    @Test
    void blueColorValue_shouldIncreaseByOne() {
        image = new BufferedImage(100, 300, BufferedImage.TYPE_INT_BGR);
        color = new Color(255, 20, 125);
        generator = new VerticalLinearGradientGenerator(image, color);
        generator.generateImage();

        int numberOfStepsWithIncreaseValueBiggerThanOne = IntStream.range(0, image.getHeight())
                .mapToObj(i -> new Color(image.getRGB(0, i)))
                .mapToInt(Color::getBlue)
                .reduce(this::checkColorIncreaseValue)
                .getAsInt() % RGBColorValues.MAXIMUM_VALUE;

        assertEquals(0, numberOfStepsWithIncreaseValueBiggerThanOne);
    }

    private int checkColorIncreaseValue(int f, int l) {
        System.out.println(f + ": " + l);
        return  f == l || f == l - 1 ? l : 0;
    }

}