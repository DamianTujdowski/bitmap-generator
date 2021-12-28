public class Main {
    private static ImageGenerator generator;

    public static void main(String[] args) {
        generator = new GradientGenerator(100, 50);
        generator.generateImage();
        generator.saveImage();
    }
}
