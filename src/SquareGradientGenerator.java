import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SquareGradientGenerator implements ImageGenerator {

    private BufferedImage image;
    private int width;
    private int height;

    public SquareGradientGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
    }

    //TODO add color gradient to shape
    @Override
    public void generateImage() {
        int red = 255, blue = 0, green = 124;
        int diameter = Math.min(width, height);
        int middle = diameter / 2;

        for (int x = 0; x < middle; x++) {
            for (int y = middle - x; y < middle + x; y++) {
                int pixel = (red << 24) | (green << 8) | blue;
                image.setRGB(y, x, pixel);
            }
        }

        for (int x = middle; x < diameter; x++) {
            for (int y = Math.abs(middle - x); y < diameter - Math.abs(middle - x); y++) {
                int pixel = (red << 24) | (green << 8) | blue;
                image.setRGB(y, x, pixel);
            }
        }
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
