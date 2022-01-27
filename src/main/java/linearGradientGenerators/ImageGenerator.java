package linearGradientGenerators;

public interface ImageGenerator {
    void generateImage();
    void saveImage(String fileFormat, String path);
}
