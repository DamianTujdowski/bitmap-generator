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

    @Test
    void blueColorValue_shouldIncreaseByOne() {
        image = new BufferedImage(100, 300, BufferedImage.TYPE_INT_BGR);
        generator = new VerticalLinearGradientGenerator(image, 255, 20, 125);
        generator.generateImage();

        int numberOfStepsWithIncreaseValueBiggerThanOne = IntStream.range(0, image.getHeight())
                .mapToObj(i -> new Color(image.getRGB(0, i)))
                .mapToInt(Color::getBlue)
                .reduce(this::checkColorIncreaseValue)
                .getAsInt() % RGBColorValues.MAXIMUM_VALUE;

        assertEquals(0, numberOfStepsWithIncreaseValueBiggerThanOne);
    }

    private int checkColorIncreaseValue(int f, int l) {
        return  f == l || f == l - 1 ? l : 0;
    }

}