import constants.RGBColorValues;
import linearGradientGenerators.HorizontalLinearGradientGenerator;
import linearGradientGenerators.LinearGradientGenerator;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class HorizontalLinearGradientGeneratorTest {

    LinearGradientGenerator generator;
    BufferedImage image;

    @Test
    void blueColorValue_shouldIncreaseByOne() {
        image = new BufferedImage(300, 100, BufferedImage.TYPE_INT_BGR);
        generator = new HorizontalLinearGradientGenerator(image, 255, 20, 125);
        generator.generateImage();

        int numberOfStepsWithIncreaseValueBiggerThanOne = IntStream.range(0, image.getWidth())
                .mapToObj(i -> new Color(image.getRGB(i, 0)))
                .mapToInt(Color::getBlue)
                .reduce(this::checkColorIncreaseValue)
                .getAsInt() % RGBColorValues.MAXIMUM_VALUE;

        assertEquals(0, numberOfStepsWithIncreaseValueBiggerThanOne);
    }

    private int checkColorIncreaseValue(int f, int l) {
        return  f == l || f == l - 1 ? l : 0;
    }

}