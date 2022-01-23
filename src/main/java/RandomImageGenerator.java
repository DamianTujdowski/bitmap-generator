import linearGradientGenerators.ImageGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RandomImageGenerator implements ImageGenerator {
    private BufferedImage image;
    private int width;
    private int height;

    public RandomImageGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void generateImage() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int red = (int) (Math.random() * 256);
                int green = (int) (Math.random() * 256);
                int blue = (int) (Math.random() * 256);

                int pixel = (red << 16) | (green << 8) | blue;

                image.setRGB(x, y, pixel);
            }
        }
    }

    @Override
    public void saveImage() {
        try {
            File file = new File("B:\\Programowanie\\Aplikacje\\imageGenerator\\src\\pics\\random_image.jpeg");
            ImageIO.write(image, "jpeg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
