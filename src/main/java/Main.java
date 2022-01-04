public class Main {
    private static ImageGenerator generator;

    public static void main(String[] args) {
        generator = new RadialGradientGenerator(500, 450, 255, 105,200);
        generator.generateImage();
        generator.saveImage();
//        RadialGradientGenerator rad = new RadialGradientGenerator(500, 450);
//        System.out.println(rad.distanceFromCenter(10,10));
    }

    //TODO separate creating BufferedImage from generating gradient
}
