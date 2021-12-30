import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RadialGradientGenerator implements ImageGenerator {
    private BufferedImage image;
    private int width;
    private int height;

    public RadialGradientGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void generateImage() {

    }


    @Override
    public void saveImage() {
        try {
            File file = new File("B:\\Programowanie\\Aplikacje\\imageGenerator\\src\\pics\\gradient.jpeg");
            ImageIO.write(image, "jpeg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
