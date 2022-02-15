import constants.RGBColorValues;
import linearGradientGenerators.HorizontalLinearGradientGenerator;
import linearGradientGenerators.LinearGradientGenerator;
import linearGradientGenerators.VerticalLinearGradientGenerator;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VerticalLinearGradientGeneratorTest {
    LinearGradientGenerator generator;
    BufferedImage image;
    Color color;

    @Test
    void redColorValue_shouldIncreaseByByZeroOrOne() {
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
    void greenColorValue_shouldIncreaseByByZeroOrOne() {
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
    void greenColorValue_noNullPointerExceptionIsThrown() {
        image = new BufferedImage(100, 300, BufferedImage.TYPE_INT_BGR);
        color = new Color(255, 125, 125);
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
    void blueColorValue_shouldIncreaseByZeroOrOne() {
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

    @Test
    void blueColorValue_noNullPointerExceptionIsThrown() {
        image = new BufferedImage(100, 300, BufferedImage.TYPE_INT_BGR);
        color = new Color(255, 20, 10);
        generator = new VerticalLinearGradientGenerator(image, color);
        generator.generateImage();

        int numberOfStepsWithIncreaseValueBiggerThanOne = IntStream.range(0, image.getHeight())
                .mapToObj(i -> new Color(image.getRGB(0, i)))
                .mapToInt(Color::getBlue)
                .reduce(this::checkColorIncreaseValue)
                .getAsInt() % RGBColorValues.MAXIMUM_VALUE;

        assertEquals(0, numberOfStepsWithIncreaseValueBiggerThanOne);
    }

    @Test
    void redColorHas255InputValue_shouldNotChange() {
        image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_BGR);
        color = new Color(255, 20, 125);
        generator = new VerticalLinearGradientGenerator(image, color);
        generator.generateImage();

        int sumOfPixelColorValuesInRow = IntStream.range(0, image.getWidth())
                .mapToObj(i -> new Color(image.getRGB(0, i)))
                .mapToInt(Color::getRed)
                .sum();

        int height = image.getHeight();
        int expected = sumOfPixelColorValuesInRow / height;

        int redColorValue = color.getRed();

        assertEquals(expected, redColorValue);
    }

    private int checkColorIncreaseValue(int f, int l) {
        return  f == l || f == l - 1 ? l : 0;
    }

}