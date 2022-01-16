import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

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

}