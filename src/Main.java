public class Main {
    private static ImageGenerator generator;

    public static void main(String[] args) {
        generator = new RadialGradientGenerator(500, 450);
        generator.generateImage();
        generator.saveImage();
    }

    //TODO separate creating BufferedImage from generating gradient
}
