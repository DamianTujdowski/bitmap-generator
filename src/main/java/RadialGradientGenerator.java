import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RadialGradientGenerator implements ImageGenerator {
    //TODO move initializing BufferedImage to another class
    //TODO this class should have only radius field
    private BufferedImage image;
    private int radius;
    private List<Integer> chordsLengths;
    public Map<Integer, Integer> blueGradientValues;
    private int red;
    private int green;
    private int blue;

    public RadialGradientGenerator(int width, int height, int red, int green, int blue) {
        this.radius = Math.min(width, height) / 2;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        chordsLengths = new ArrayList<>();
        blueGradientValues = new LinkedHashMap<>();
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public void generateImage() {
        int diameter = radius * 2;

        fillBlueGradientValues();
        countAllChordsLengths();

        for (int x = 0; x < radius; x++) {
            int chordHalf = chordsLengths.get(radius - x - 1) / 2;
            int leftBoundary = radius - chordHalf;
            int rightBoundary = radius + chordHalf;

            for (int y = leftBoundary; y < rightBoundary; y++) {
                int distance = distanceFromCenter(x, y);
                int key = distance - distance % 3;
                blue = blueGradientValues.get(key);
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

    public void fillBlueGradientValues() {
        int blueColorSteps = 255 - blue;
        int interval = radius / blueColorSteps;

        IntStream.iterate(0, n -> n + interval)
                .limit(blueColorSteps)
                .forEach(num -> blueGradientValues.put(num, blue++));
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
        double radiusSquared = Math.pow(radius, 2);
        double distanceFromCenterSquared = Math.pow(distanceFromCenter, 2);

        return (int) (2 * Math.sqrt(radiusSquared - distanceFromCenterSquared));
    }
}
