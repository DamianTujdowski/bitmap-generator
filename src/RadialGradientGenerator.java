import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RadialGradientGenerator implements ImageGenerator {
    //TODO move initializing BufferedImage to another class
    //TODO this class should have only radius field
    private BufferedImage image;
    private int radius;

    public RadialGradientGenerator(int width, int height) {
        this.radius = Math.min(width, height) / 2;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void generateImage() {
        int red = 255, blue = 0, green = 124;
        int diameter = radius * 2;

        for (int x = 0; x < radius; x++) {
            int chord = countChordLength(this.radius - x);
            int chordHalf = chord / 2;
            for (int y = radius - chordHalf; y < radius + chordHalf; y++) {
                int pixel = (red << 24) | (green << 8) | blue;
                image.setRGB(y, x, pixel);
            }
        }

        for (int x = radius; x < diameter; x++) {
            int chord = countChordLength(this.radius - x);
            int chordHalf = chord / 2;
            for (int y = Math.abs(radius - chordHalf); y < diameter - Math.abs(radius - chordHalf); y++) {
                int pixel = (red << 24) | (green << 8) | blue;
                image.setRGB(y, x, pixel);
            }
        }
    }

    @Override
    public void saveImage() {
        try {
            File file = new File("src\\pics\\radialGradient.jpeg");
            ImageIO.write(image, "jpeg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int countChordLength(int distanceFromCenter) {
        return (int) (2 * Math.sqrt(
                Math.pow(radius, 2) - Math.pow(distanceFromCenter, 2)
        ));
    }
}
