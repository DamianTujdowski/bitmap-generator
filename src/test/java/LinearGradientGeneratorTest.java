import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class LinearGradientGeneratorTest {

    LinearGradientGenerator generator;
    BufferedImage image;

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