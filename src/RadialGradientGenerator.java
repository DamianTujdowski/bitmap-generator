import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RadialGradientGenerator implements ImageGenerator {
    //TODO move initializing BufferedImage to another class
    //TODO this class should have only radius field
    private BufferedImage image;
    private int radius;
    private List<Integer> chordsLengths;

    public RadialGradientGenerator(int width, int height) {
        this.radius = Math.min(width, height) / 2;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        chordsLengths = new ArrayList<>();
    }

    @Override
    public void generateImage() {
        int red = 255, blue = 0, green = 124;
        int diameter = radius * 2;

        countAllChordsLengths();

        for (int x = 0; x < radius; x++) {
            int chordHalf = chordsLengths.get(radius - x - 1) / 2;
            int leftBoundary = radius - chordHalf;
            int rightBoundary = radius + chordHalf;

            for (int y = leftBoundary; y < rightBoundary; y++) {
                int pixel = (red << 24) | (green << 8) | blue;
                image.setRGB(y, x, pixel);
            }
        }

        for (int x = radius; x < diameter; x++) {
            int chordHalf = chordsLengths.get(x - radius) / 2;
            int leftBoundary = Math.abs(radius - chordHalf);
            int rightBoundary = diameter - Math.abs(radius - chordHalf);

            for (int y = leftBoundary; y < rightBoundary; y++) {
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

    public int distanceFromCenter(int x, int y) {
        return (int) Math.sqrt(x * x + y * y);
    }

    private void countAllChordsLengths() {
        chordsLengths = IntStream.range(0, radius)
                .map(this::countChordLength)
                .boxed()
                .collect(Collectors.toList());
    }

    private int countChordLength(int distanceFromCenter) {
        return (int) (2 * Math.sqrt(
                Math.pow(radius, 2) - Math.pow(distanceFromCenter, 2)
        ));
    }
}
